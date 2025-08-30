// Copyright (c) 2023-2025 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: AGPL-3.0-or-later
// This file is part of https://github.com/texsydo/texsydo---mvp

package software.math.tsd.webmvp.io

import java.nio.file.Path
import kotlin.io.path.name

class Entry(val rootPath: Path, val relPath: Path = Path.of(""))

fun newEntryFromAbsPath(rootPath: Path, absPath: Path): Entry = Entry(
    rootPath,
    Path.of(absPath.toString().removePrefix(rootPath.toString()))
)

val Entry.path: Path
    get() = Path.of(rootPath.toString(), relPath.toString())

fun Entry.name(): String = path.name
