// Copyright (c) 2025 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: AGPL-3.0-or-later
// This file is part of https://github.com/texsydo/texsydo---mvp

package software.math.tsd.webmvp

import software.math.tsd.webmvp.TextSource.*

sealed interface TextSource {
    data object PlainText : TextSource
}

fun TextSource.extension(): String = when (this) {
    PlainText -> "txt"
}
