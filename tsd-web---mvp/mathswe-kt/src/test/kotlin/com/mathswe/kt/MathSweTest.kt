// Copyright (c) 2025 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: AGPL-3.0-or-later
// This file is part of https://github.com/texsydo/texsydo---mvp

package com.mathswe.kt

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class MathSweTest : StringSpec({
    "pipe operator should apply functions in sequence" {
        val double = { x: Int -> x * 2 }
        val inc = { x: Int -> x + 1 }
        val result = 3 `---` double `---` inc // (3 * 2) + 1 = 7

        result shouldBe 7
    }

    "composition operator should compose functions correctly" {
        val double = { x: Int -> x * 2 }
        val inc = { x: Int -> x + 1 }
        val composed = inc o double

        composed(4) shouldBe 9
        composed(1) shouldBe 3
    }

    "application operator should apply value to function" {
        val square = { x: Int -> x * x }
        val result = square `$` 5

        result shouldBe 25
    }

    "operators should work together in a chain" {
        val double = { x: Int -> x * 2 }
        val inc = { x: Int -> x + 1 }
        val square = { x: Int -> x * x }
        val result = 3 `---` double `---` inc `---` square

        result shouldBe 49 // ((3 * 2) + 1)^2
    }
})
