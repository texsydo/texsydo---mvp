// Copyright (c) 2025 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: AGPL-3.0-or-later
// This file is part of https://github.com/texsydo/texsydo---mvp

package software.math.tsd.webmvp.dorep.jekyll

import arrow.core.Either
import arrow.core.None
import arrow.core.Option
import arrow.core.Some
import arrow.core.getOrElse
import arrow.core.left
import arrow.core.raise.either
import arrow.core.some
import software.math.tsd.webmvp.TextSource.*
import software.math.tsd.webmvp.fromLowercaseToTitleCase
import software.math.tsd.webmvp.io.Entry
import software.math.tsd.webmvp.io.loadContent
import software.math.tsd.webmvp.io.path
import software.math.tsd.webmvp.io.textSource
import software.math.tsd.webmvp.io.title
import software.math.tsd.webmvp.md.plainTextToMarkdownContent
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.nameWithoutExtension

data class JekyllArticleOps(
    val entry: Entry,
    val index: Option<JekyllIndex>,
    val env: Option<JekyllBuildingEnvironment>,
    val dorep: Option<JekyllDoRepCli>,
)

fun jekyllArticleOpsFrom(entry: Entry): JekyllArticleOps =
    JekyllArticleOps(entry, index = None, env = None, dorep = None)

fun JekyllArticleOps.loadIndex(): Either<String, JekyllArticleOps> =
    either {
        val markdownContent = loadMarkdownContent().bind()
        val rootEntry = Entry(entry.rootPath)
        val homeName = rootEntry.title.fromLowercaseToTitleCase()
        val permalink = entry.path.nameWithoutExtension
        val title = markdownContent.extractTitle()
        val abstract = markdownContent.extractAbstract()
        val index = JekyllIndex(
            FrontMatter(
                permalink,
                title,
                Some(abstract),
                ogimage = None,
            ),
            markdownContent.generateNav(homeName),
            markdownContent.generateToC(),
            markdownContent.parse(),
            None,
        )

        copy(index = index.some())
    }

fun JekyllArticleOps.buildEnvironment(): Either<String, JekyllArticleOps> = either {
    println("⚙ Build Jekyll environment")

    val index = index.getOrNull() ?: "Jekyll index is not loaded.".left().bind()

    val jekyllBuild = newJekyllBuildingEnvironment(
        entry.rootPath.toAbsolutePath()
    )

    val indexPath = jekyllBuild.copyIndex(entry, index).bind()

    println("\uD83D\uDEC8 Source: file:///${jekyllBuild.src}")
    println("\uD83D\uDEC8 Destination: file:///${jekyllBuild.dst}")
    println("\uD83D\uDEC8 Markdown index copied to file:///$indexPath")

    jekyllBuild.copyJekyllMetaDirectories().bind()

    println("✔\uFE0F Copy Jekyll Meta")

    jekyllBuild.saveNavigation(index).bind()

    println("✔\uFE0F Copy Article Navigation")

    jekyllBuild.saveToc(index).bind()

    println("✔\uFE0F Copy Article TOC")

    println("✅ Build Jekyll environment")

    copy(env = jekyllBuild.some())
}

fun JekyllArticleOps.installTempJekyllDoRepCli() = either {
    val cli = newTempJekyllDoRepInstaller()
        .bind()
        .install()
        .bind()

    copy(dorep = Some(cli))
}

fun JekyllArticleOps.build(): Either<String, Path> = either {
    println("⚙ Jekyll Build")

    val env = env
        .getOrElse {
            "Building environment not loaded.".left().bind()
        }
    val jekyllBuildSrc = env.dst

    val dstPath = try {
        Files
            .createTempDirectory("dorep-for-jekyll---web---tsd-web---mvp")
    }
    catch (e: IOException) {
        e.toString().left().bind()
    }
    println("✔\uFE0F Create temporal build directory")


    dorep
        .getOrElse {
            """
                |DoRep for Jekyll CLI not installed. See
                |`installTempJekyllDoRepCli`.
            """
                .trimMargin("|")
                .left()
                .bind()
        }
        .build(jekyllBuildSrc, dstPath)

    val dist = dstPath.resolve("_site")

    println("\uD83D\uDEC8 Article built at file:///$dist")
    println("✅ Jekyll Build")

    dist
}

private fun JekyllArticleOps.loadMarkdownContent() = either {
    val source = entry
        .textSource()
        .getOrElse {
            "Entry source format unknown: $entry".left().bind()
        }

    val content = loadContent(entry)
        .getOrElse {
            "Fail to load entry content: $entry".left().bind()
        }

    when (source) {
        PlainText -> content.plainTextToMarkdownContent()
    }
}
