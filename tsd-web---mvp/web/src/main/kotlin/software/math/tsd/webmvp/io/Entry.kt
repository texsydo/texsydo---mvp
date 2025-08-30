// Copyright (c) 2023-2025 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: AGPL-3.0-or-later
// This file is part of https://github.com/texsydo/texsydo---mvp

package software.math.tsd.webmvp.io

import arrow.core.Option
import arrow.core.none
import arrow.core.some
import software.math.tsd.webmvp.TextSource
import software.math.tsd.webmvp.TextSource.*
import software.math.tsd.webmvp.Title
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.name
import kotlin.io.path.nameWithoutExtension

data class Entry(val rootPath: Path, val relPath: Path = Path.of(""))

val Entry.path: Path
    get() = Path.of(rootPath.toString(), relPath.toString())

val Entry.id: String
    get() = path.nameWithoutExtension

val Entry.title: Title
    get() = Title(id)

val Entry.name: String
    get() = path.name

fun newEntryFromAbsPath(rootPath: Path, absPath: Path): Entry = Entry(
    rootPath,
    Path.of(absPath.toString().removePrefix(rootPath.toString()))
)

fun Entry.textSource(): Option<TextSource> =
    when (path.toString().substringAfterLast('.', missingDelimiterValue = "")) {
        "txt" -> PlainText.some()
        else  -> none()
    }

data class Content(val entry: Entry, val content: String)

fun loadContent(entry: Entry): Option<Content> =
    entry.textSource().map { _ ->
        val text = Files.readString(entry.path)
        Content(entry, text)
    }
