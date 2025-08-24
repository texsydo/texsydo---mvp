// Copyright (c) 2023-2025 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: AGPL-3.0-or-later
// This file is part of https://github.com/texsydo/texsydo---mvp

package software.math.tsd.webmvp.cli

import arrow.core.Either
import arrow.core.None
import arrow.core.right
import java.util.*

enum class Cmd {
    Build,
}

fun newOp(value: String): Either<None, Cmd> = try {
    value
        .split("-")
        .joinToString(separator = "") { str ->
            str.replaceFirstChar { ch ->
                ch.titlecase(Locale.getDefault())
            }
        }
        .let { Cmd.valueOf(it) }
        .right()
}
catch (e: IllegalArgumentException) {
    Either.Left(None)
}
