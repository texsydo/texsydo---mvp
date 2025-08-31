// Copyright (c) 2025 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: AGPL-3.0-or-later
// This file is part of https://github.com/texsydo/texsydo---mvp

package software.math.tsd.webmvp.txt

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import software.math.tsd.webmvp.Leaf
import software.math.tsd.webmvp.io.Content
import software.math.tsd.webmvp.io.Entry
import java.nio.file.Files
import kotlin.io.path.createTempDirectory
import kotlin.io.path.writeText

class PlainTextArticleTest : StringSpec({
    "plainTextToArticle should build article with abstract and leaves" {
        val root = createTempDirectory("root")
        val file = root.resolve("complete-doc.txt")
        file.writeText(
            """
            First paragraph is abstract.

            Second paragraph is a leaf.

            Third paragraph is another leaf.
            """.trimIndent()
        )
        val entry = Entry(root, file.fileName)
        val content = Content(entry, Files.readString(file))
        val article = content.plainTextToArticle()

        article.title.title shouldBe "complete-doc"
        article.abstract.title shouldBe "First paragraph is abstract."
        article.content.map { (it as Leaf<*>).content } shouldBe listOf(
            "Second paragraph is a leaf.",
            "Third paragraph is another leaf."
        )
    }

    "plainTextToArticle should return empty abstract and no leaves for empty file" {
        val root = createTempDirectory("root")
        val file = root.resolve("empty.txt")

        file.writeText("")

        val entry = Entry(root, file.fileName)
        val content = Content(entry, "")
        val article = content.plainTextToArticle()

        article.title.title shouldBe "empty"
        article.abstract.title shouldBe ""
        article.content shouldBe emptyList()
    }

    "plainTextToArticle should use only abstract when single paragraph" {
        val root = createTempDirectory("root")
        val file = root.resolve("single.txt")

        file.writeText("Just one paragraph")

        val entry = Entry(root, file.fileName)
        val content = Content(entry, "Just one paragraph")

        val article = content.plainTextToArticle()

        article.title.title shouldBe "single"
        article.abstract.title shouldBe "Just one paragraph"
        article.content shouldBe emptyList()
    }
})
