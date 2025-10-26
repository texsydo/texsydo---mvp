// Copyright (c) 2023-2025 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: AGPL-3.0-or-later
// This file is part of https://github.com/texsydo/texsydo---mvp

package software.math.tsd.webmvp.md

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import software.math.tsd.webmvp.io.Content
import software.math.tsd.webmvp.io.Entry
import kotlin.io.path.Path

class MarkdownContentTest : StringSpec({
    val plainText = """
        |Abstract section.
        |
        |More plain text sections.
        |
        |Final paragraph.
    """.trimMargin("|")

    "should convert plain text to markdown" {
        val entry = Entry(Path("plain-text-article.txt"))
        val content = Content(entry, plainText)
        val md = content.plainTextToMarkdownContent()

        md.value shouldBe """
            |# Plain Text Article
            |
            |Abstract section.
            |
            |More plain text sections.
            |
            |Final paragraph.
    """.trimMargin("|")
    }
})
