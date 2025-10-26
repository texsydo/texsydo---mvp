// Copyright (c) 2023-2025 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: AGPL-3.0-or-later
// This file is part of https://github.com/texsydo/texsydo---mvp

package software.math.tsd.webmvp

import arrow.core.Option

data class Title(val title: String)

data class Cover(val title: String)

data class Abstract(val title: String)

data class Article(
    val title: Title,
    val cover: Option<Cover>,
    val abstract: Abstract,
    val content: List<ArticleNode>,
)

sealed interface ArticleNode
data class Leaf<T>(val content: T) : ArticleNode
data class SubArticle(val article: Article) : ArticleNode
