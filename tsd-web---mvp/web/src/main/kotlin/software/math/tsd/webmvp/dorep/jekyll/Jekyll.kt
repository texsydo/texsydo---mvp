// Copyright (c) 2023-2025 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: AGPL-3.0-or-later
// This file is part of https://github.com/texsydo/texsydo---mvp

package software.math.tsd.webmvp.dorep.jekyll

import arrow.core.Either
import arrow.core.None
import arrow.core.Option
import arrow.core.getOrElse
import arrow.core.left
import arrow.core.right
import software.math.tsd.webmvp.md.MarkdownContent
import java.io.IOException
import java.nio.file.Path
import kotlin.io.path.writeText

data class FrontMatter(
    val permalink: String,
    val title: String,
    val description: Option<String> = None,
    val ogimage: Option<String> = None,
)

fun FrontMatter.toMarkdownString(): String {
    val strOf: (String, Option<String>) -> String = { attr, value ->
        value
            .map { it.replace("\n", " ") }
            .map {
                """
                
                $attr: "${it.replace("\"", "\\\"")}"
                """.trimIndent()
            }
            .getOrElse { "" }
    }

    return StringBuilder()
        .append("---")
        .append("\n")
        .append("permalink: $permalink")
        .append("\n")
        .append("""title: "$title"""")
        .append(strOf("description", description))
        .append(strOf("ogimage", ogimage))
        .append("\n")
        .append("---")
        .append("\n")
        .toString()
}

data class JekyllIndex(
    val frontMatter: FrontMatter,
    val nav: Nav,
    val toc: Nav,
    val index: MarkdownContent,
    val subdirNav: Option<Div>,
)

fun JekyllIndex.toMarkdownString(): String = """
    |'${frontMatter.toMarkdownString()}
    |'
    |'$index
    |'
    |'${subdirNav.map(Div::toHtmlString).getOrElse { "" }}
""".trimMargin("|'")
