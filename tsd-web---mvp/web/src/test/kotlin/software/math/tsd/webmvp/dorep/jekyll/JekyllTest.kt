// Copyright (c) 2023-2025 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: AGPL-3.0-or-later
// This file is part of https://github.com/texsydo/texsydo---mvp

package software.math.tsd.webmvp.dorep.jekyll

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class FrontMatterTest : StringSpec({
    val frontMatter = FrontMatter(
        "article",
        "Article Title"
    )

    "should convert to markdown" {
        frontMatter.toMarkdownString() shouldBe """
            ---
            permalink: article
            title: "Article Title"
            ---
            
        """.trimIndent()
    }
})
