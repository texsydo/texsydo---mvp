// Copyright (c) 2025 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: AGPL-3.0-or-later
// This file is part of https://github.com/texsydo/texsydo---mvp

package software.math.tsd.webmvp.md

import software.math.tsd.webmvp.fromLowercaseToTitleCase
import software.math.tsd.webmvp.io.Content
import software.math.tsd.webmvp.io.title

data class MarkdownContent(val value: String) {
    override fun toString(): String = value
}

fun Content.plainTextToMarkdownContent(): MarkdownContent =
    MarkdownContent(
        value = """
            |# ${entry.title.fromLowercaseToTitleCase()}
            |
            |$content
        """.trimMargin("|")
    )
