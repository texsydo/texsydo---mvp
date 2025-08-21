// Copyright (c) 2025 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: AGPL-3.0-or-later
// This file is part of https://github.com/texsydo/texsydo---mvp

package software.math.tsd.webmvp.cli

import arrow.core.Either
import arrow.core.Either.Left
import arrow.core.None
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CmdTest {
    @Test
    fun lowercaseStringToCmd() {
        val result = newOp("build")

        assertEquals(Either.Right(Cmd.Build), result)
    }

    @Test
    fun capitalizedStringToCmd() {
        val result = newOp("Build")

        assertEquals(Either.Right(Cmd.Build), result)
    }

    @Test
    fun mixedCaseStringToCmd() {
        val result = newOp("BuIlD")

        assertEquals(Left(None), result)
    }

    @Test
    fun unknownStringToLeftNone() {
        val result = newOp("unknown-command")

        assertEquals(Left(None), result)
    }

    @Test
    fun dashedStringWithoutEnumToLeftNone() {
        val result = newOp("build-op")

        assertEquals(Left(None), result)
    }
}
