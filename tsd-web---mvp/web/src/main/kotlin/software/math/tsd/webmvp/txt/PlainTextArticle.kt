// Copyright (c) 2025 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: AGPL-3.0-or-later
// This file is part of https://github.com/texsydo/texsydo---mvp

package software.math.tsd.webmvp.txt

import arrow.core.None
import software.math.tsd.webmvp.Abstract
import software.math.tsd.webmvp.Article
import software.math.tsd.webmvp.Leaf
import software.math.tsd.webmvp.io.Content
import software.math.tsd.webmvp.io.title

fun Content.plainTextToArticle(): Article {
    // Split paragraphs by double newlines, trim empty ones
    val paragraphs = content
        .split(Regex("""\n\s*\n"""))
        .map { it.trim() }
        .filter { it.isNotEmpty() }

    val abstractText = paragraphs.firstOrNull().orEmpty()
    val remaining =
        if (paragraphs.isEmpty()) emptyList() else paragraphs.drop(1)

    return Article(
        title = entry.title,
        cover = None,
        abstract = Abstract(abstractText),
        content = remaining.map { Leaf(it) }
    )
}
