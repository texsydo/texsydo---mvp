// Copyright (c) 2025 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: AGPL-3.0-or-later
// This file is part of https://github.com/texsydo/texsydo---mvp

package software.math.tsd.webmvp.io

import arrow.core.none
import arrow.core.some
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import software.math.tsd.webmvp.TextSource
import java.nio.file.Files
import kotlin.io.path.Path

class EntryTest : StringSpec({
    "should create Entry with default relative path" {
        val root = Path("/project")
        val entry = Entry(root)

        entry.rootPath shouldBe root
        entry.relPath shouldBe Path("")
        entry.path shouldBe root
        entry.name() shouldBe "project"
    }

    "should create Entry with explicit relative path" {
        val root = Path("/project")
        val rel = Path("src/main")
        val entry = Entry(root, rel)

        entry.rootPath shouldBe root
        entry.relPath shouldBe rel
        entry.path shouldBe Path("/project/src/main")
        entry.name() shouldBe "main"
    }

    "should build Entry from absolute path" {
        val root = Path("/project")
        val abs = Path("/project/src/file.txt")

        val entry = newEntryFromAbsPath(root, abs)

        entry.rootPath shouldBe root
        entry.relPath.toString() shouldBe "/src/file.txt" // may include leading "/"
        entry.path shouldBe abs
        entry.name() shouldBe "file.txt"
    }

    "should handle root and absolute path being the same" {
        val root = Path("/project")
        val abs = Path("/project")

        val entry = newEntryFromAbsPath(root, abs)

        entry.relPath.toString() shouldBe "" // empty because same path
        entry.path shouldBe root
        entry.name() shouldBe "project"
    }
})

class ContentTest : StringSpec({
    "Entry.textSource should detect .txt" {
        val root = Files.createTempDirectory("root")
        val file = Files.createFile(root.resolve("file.txt"))
        val entry = Entry(root, file.fileName)

        entry
            .textSource()
            .getOrNull() shouldBe TextSource.PlainText
            .some()
            .getOrNull()
    }

    "Entry.textSource should return none for non-txt" {
        val root = Files.createTempDirectory("root")
        val file = Files.createFile(root.resolve("file.docx"))
        val entry = Entry(root, file.fileName)

        entry.textSource() shouldBe none()
    }

    "loadContent should load plain text content" {
        val root = Files.createTempDirectory("root")
        val file = root.resolve("hello.txt")
        Files.writeString(file, "Hello, world!")
        val entry = Entry(root, file.fileName)

        val content = loadContent(entry).getOrNull()

        content!!.content shouldBe "Hello, world!"
    }

    "loadContent should return none for unsupported extension" {
        val root = Files.createTempDirectory("root")
        val file = Files.createFile(root.resolve("note.docx"))
        val entry = Entry(root, file.fileName)

        loadContent(entry) shouldBe none()
    }
})
