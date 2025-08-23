// Copyright (c) 2025 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: AGPL-3.0-or-later
// This file is part of https://github.com/texsydo/texsydo---mvp

package com.mathswe.kt

import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class MathSweTest {
    @Test
    fun testPipeOperator() {
        val double = { x: Int -> x * 2 }
        val inc = { x: Int -> x + 1 }
        val result = 3 `---` double `---` inc // (3 * 2) + 1 = 7

        assertEquals(7, result)
    }

    @Test
    fun testCompositionOperator() {
        val double = { x: Int -> x * 2 }
        val inc = { x: Int -> x + 1 }
        val composed = inc o double // inc(double(x)) = (x * 2) + 1

        assertEquals(9, composed(4))
        assertEquals(3, composed(1))
    }

    @Test
    fun testApplicationOperator() {
        val square = { x: Int -> x * x }
        val result = square `$` 5 // square(5) = 25

        assertEquals(25, result)
    }

    @Test
    fun testOperatorsTogether() {
        val double = { x: Int -> x * 2 }
        val inc = { x: Int -> x + 1 }
        val square = { x: Int -> x * x }

        // Equivalent to: square(inc(double(3)))
        val result = 3 `---` double `---` inc `---` square

        assertEquals(49, result) // ((3 * 2) + 1)^2 = 49
    }
}