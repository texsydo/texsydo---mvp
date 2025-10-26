// Copyright (c) 2025 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: AGPL-3.0-or-later
// This file is part of https://github.com/texsydo/texsydo---mvp

package software.math.tsd.webmvp.dorep.jekyll

import arrow.core.Either
import arrow.core.left
import arrow.core.raise.either
import arrow.core.right
import software.math.tsd.webmvp.io.Entry
import software.math.tsd.webmvp.io.id
import system.file.copyDirectory
import system.file.deleteDirectory
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.*

data class JekyllBuildingEnvironment(
    val src: Path,
    val dst: Path,
)

fun JekyllBuildingEnvironment.getJekyllEntryDirectory(
    entry: Entry,
) = with(dst.resolve(entry.relPath)) {
    if (name.contains('.')) parent.resolve(entry.id)
    else this
}

fun JekyllBuildingEnvironment.copyJekyllSource(): Either<String, Unit> = try {
    copyDirectory(src, dst)
}
catch (e: IOException) {
    e.toString().left()
}

fun JekyllBuildingEnvironment.clearJekyllEntry(
    entry: Entry,
): Either<String, Path> = either {
    val entryPath = dst.resolve(entry.relPath)

    if (entryPath.exists()) {
        if (entryPath.isDirectory()) {
            // For example, blog/math/pi/pi.txt
            deleteDirectory(entryPath)
                .mapLeft { "Failed to delete Jekyll entry ${entryPath}: $it" }
                .bind()
        }
        else {
            // For example, blog/math/pi.txt
            entryPath.deleteIfExists()
        }
    }

    val entryDir = getJekyllEntryDirectory(entry)

    entryDir.createDirectory()

    entryDir
}

fun JekyllBuildingEnvironment.copyJekyllMetaDirectories(): Either<String, Unit> =
    either {
        val dataDir = src.resolve("_data")

        if (dataDir.exists()) {
            copyDirectory(dataDir, dst.resolve("_data"))
                .bind()
        }
    }

fun JekyllBuildingEnvironment.copyIndex(
    entry: Entry,
    content: JekyllIndex,
): Either<String, Path> = either {
    val entryDir = clearJekyllEntry(entry).bind()
    val entryIndex = entryDir.resolve("index.md")
    val md = content.toMarkdownString()

    try {
        entryIndex.writeText(md).right()
    }
    catch (e: IOException) {
        "Fail write text at $entryIndex for $entryDir: $e".left()
    }.bind()

    entryIndex
}

fun JekyllBuildingEnvironment.saveNavigation(
    index: JekyllIndex,
): Either<String, Unit> =
    saveInclude(dst, "nav.html", index.nav.toHtmlString())

fun JekyllBuildingEnvironment.saveToc(
    index: JekyllIndex,
): Either<String, Unit> =
    saveInclude(
        dst,
        "${index.frontMatter.permalink}_toc.html",
        index.toc.toHtmlString()
    )

fun newJekyllBuildingEnvironment(src: Path): JekyllBuildingEnvironment {
    val dst = Files.createTempDirectory(
        "build---dorep-for-jekyll---web---tsd-web---mvp"
    )

    return JekyllBuildingEnvironment(
        src,
        dst,
    )
}

private fun saveInclude(
    root: Path,
    file: String,
    content: String,
): Either<String, Unit> =
    try {
        val includesPath = root.resolve("_includes")

        Files.createDirectories(includesPath)

        includesPath
            .resolve(file)
            .writeText(content)
            .right()
    }
    catch (e: IOException) {
        e.toString().left()
    }
