// Copyright (c) 2023-2025 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: AGPL-3.0-or-later
// This file is part of https://github.com/texsydo/texsydo---mvp

package software.math.tsd.webmvp

import com.mathswe.kt.`$`

object Error {
    var failed: Boolean = false
        private set

    fun handle(errorMsg: String): (String) -> Unit = { cause ->
        print("$errorMsg: $cause")
    }

    fun print(errorMsg: String) {
        failed = true
        println("❌ $errorMsg")
    }
}

val handleError: (String) -> (String) -> Unit = { Error::handle `$` it }
val printError: (String) -> Unit = { Error::print `$` it }
