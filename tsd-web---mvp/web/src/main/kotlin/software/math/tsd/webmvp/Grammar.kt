// Copyright (c) 2025 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: AGPL-3.0-or-later
// This file is part of https://github.com/texsydo/texsydo---mvp

package software.math.tsd.webmvp

fun Title.fromLowercaseToTitleCase(
): String = title
    .split('-', '_') // handle both hyphens and underscores
    .joinToString(" ") { part ->
        part.replaceFirstChar { it.uppercaseChar() }
    }
