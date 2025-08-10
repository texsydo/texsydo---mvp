// Copyright (c) 2023-2025 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: AGPL-3.0-or-later
// This file is part of https://github.com/texsydo/texsydo---mvp

package software.math.tsd.webmvp

import com.mathswe.kt.`$`
import software.math.tsd.webmvp.fs.getRootPath

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        printError `$` "TSD Web received no arguments to run."
        return
    }

    val arg: (Int) -> String = { args.getOrElse(it) { "" } }
    val cmd = arg(0)
    val root = getRootPath()
        .onLeft { printError `$` "Failed to load project root path: $it" }
        .getOrNull() ?: return

        args.forEach { println(it) }
    println("Running $cmd at $root")
}
