// Copyright (c) 2025 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: AGPL-3.0-or-later
// This file is part of https://github.com/texsydo/texsydo---mvp

package software.math.tsd.webmvp.io

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
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
