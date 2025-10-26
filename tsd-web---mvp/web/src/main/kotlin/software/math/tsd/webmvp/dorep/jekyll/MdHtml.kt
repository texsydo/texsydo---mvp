// Copyright (c) 2023-2025 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: AGPL-3.0-or-later
// This file is part of https://github.com/texsydo/texsydo---mvp

package software.math.tsd.webmvp.dorep.jekyll

import arrow.core.Some
import software.math.tsd.webmvp.dorep.jekyll.Attribute.*
import software.math.tsd.webmvp.md.MarkdownContent
import java.nio.file.Path
import java.util.*
import java.util.stream.Stream
import kotlin.collections.ArrayDeque
import kotlin.collections.MutableList
import kotlin.collections.last
import kotlin.collections.listOf
import kotlin.collections.map
import kotlin.collections.mapNotNull
import kotlin.collections.mapOf
import kotlin.collections.mutableListOf
import kotlin.io.path.name

fun MarkdownContent.extractTitle(): String = value
    .lines()
    .find { it.startsWith("#") }
    .orEmpty()
    .replace("# ", "")

fun MarkdownContent.extractAbstract(): String {
    var text = ""
    var abstractHashFound = false
    var coverFound = false

    for (line in value.lines()) {
        if (line.startsWith("#")) {
            abstractHashFound = true
            continue
        }
        if (!abstractHashFound) {
            continue
        }

        // If it has any cover image
        if (line.startsWith("!") || line.startsWith("<")) {
            coverFound = true
        }
        else if (line.startsWith("-")) {
            coverFound = false
            continue
        }

        if (coverFound) {
            continue
        }

        // Abstract paragraph ended
        if (line.isBlank() || line.startsWith("#")) {
            if (line.startsWith("#")) {
                break
            }
            if (text.isNotBlank()) {
                break
            }
        }

        if (text.isNotBlank()) {
            text += "\n"
        }
        text += line
    }
    return text
}

fun MarkdownContent.generateNav(home: String): Nav =
    Nav(
        attributes = mapOf(
            AriaLabel to listOf("Navigation")
        ),
        children = listOf(
            A(
                attributes = mapOf(
                    Class to listOf("home"),
                    Href to listOf("/"),
                ),
                children = listOf(
                    Span(
                        attributes = mapOf(
                            Class to listOf("material-symbols-rounded"),
                        ),
                        content = Some("home"),
                    ),
                    Span(
                        content = Some(home),
                    ),
                )
            ),
        )
    )

fun MarkdownContent.generateToC(): Nav =
    Nav(
        attributes = mapOf(
            Class to listOf("toc", "user-select-none"),
            AriaLabel to listOf("Table of Contents")
        ),
        children = listOf(
            A(
                attributes = mapOf(
                    Class to listOf("title"),
                    Href to listOf("#"),
                ),
                content = Some(extractTitle()),
            ),
            tocList(this),
        )
    )

// No code snippets, images, or automatic cover image for the current
// text-to-web migration implementation
fun MarkdownContent.parse(): MarkdownContent = this

private fun tocList(markdown: MarkdownContent): Ul {
    data class Holder(
        val indent: Int,
        val headingText: String,
        val href: String,
    )

    data class LineResult(val matchResult: MatchResult, val indent: Int)

    data class HeadingResult(val indent: Int, val headingText: String)

    // Regex to match h2 or ## headings
    val headingRegex = Regex("""^##\s*(.+)$""")

    val headingTextFromMatchResult: (LineResult) -> HeadingResult =
        { (matchResult, indent) ->
            HeadingResult(
                indent,
                matchResult
                    .groupValues[1]
                    .replaceFirst("^#+\\s*".toRegex(), "")
                    .trim()
            )
        }

    val hrefFromHeadingText: (String) -> String = { headingText ->
        headingText
            .lowercase(Locale.getDefault())
            .replace(' ', '-')
    }

    val titles = markdown
        .value
        .lines()
        .mapNotNull(headingRegex::find)
        .map { matchResult ->
            LineResult(
                matchResult,
                matchResult.value.takeWhile { it == '#' }.length - 1
            )
        }
        .map(headingTextFromMatchResult)
        .map { (indent, headingText) ->
            Holder(
                indent,
                headingText,
                hrefFromHeadingText(headingText)
            )
        }

    val rootUl = Ul(children = mutableListOf())
    val parents = ArrayDeque<Ul>()
    var currentIndent = 1

    parents.add(rootUl)
    for ((indent, headingText, href) in titles) {
        val li = Li(
            children = mutableListOf(
                A(
                    attributes = mapOf(
                        Href to listOf("#$href")
                    ),
                    content = Some(headingText),
                ),
            )
        )

        if (indent == currentIndent) {
            (parents.last().children as MutableList).add(li)
        }
        else if (currentIndent < indent) {
            while (currentIndent < indent) {
                // ...<ul><li><a></a> <ul><li><a></a>...
                val liParent = parents
                    .last()
                    .children
                    .last()
                val newParent = Ul(children = mutableListOf(li))

                (liParent.children as MutableList).add(newParent)
                parents.addLast(newParent)
                currentIndent++
            }
        }
        else {
            while (currentIndent > indent) {
                parents.removeLast()
                currentIndent--
            }
            (parents.last().children as MutableList).add(li)
        }
        currentIndent = indent
    }
    return parents.first()
}
