// Copyright (c) 2025 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: AGPL-3.0-or-later
// This file is part of https://github.com/texsydo/texsydo---mvp

package software.math.tsd.webmvp.dorep.jekyll

import arrow.core.Either
import arrow.core.None
import arrow.core.Option
import arrow.core.Some
import arrow.core.left
import arrow.core.raise.either
import arrow.core.right
import system.cmd.runCommand
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path

data class JekyllDoRepCli(
    val binPath: Path,
)

fun JekyllDoRepCli.build(src: Path, dst: Path): Either<String, Unit> = either {
    println("⚙ Build Article")
    println("\uD83D\uDEC8 Source: $src")
    println("\uD83D\uDEC8 Destination: $dst")

    runCommand("./dorep-for-jekyll build $src $dst", binPath)
        .onLeft {
            println("❌ Fail to Build")
            println("\uD83D\uDEC8 $it")
        }
        .bind()

    println("✔ Build Article")
}

data class JekyllDoRepInstaller(
    val parent: Path,
    val githubOrg: String,
    val githubRepo: String,
    val version: String,
)

val JekyllDoRepInstaller.cloneUrl: String
    get() = "https://github.com/$githubOrg/$githubRepo.git"

val JekyllDoRepInstaller.path: Path
    get() = parent.resolve("dorep-for-jekyll")

val JekyllDoRepInstaller.binPath: Path
    get() = path.resolve("build/install/dorep-for-jekyll/bin")

val JekyllDoRepInstaller.cloneCmd: String
    get() = "git clone $cloneUrl"

val JekyllDoRepInstaller.tag: Option<String>
    get() = when (version) {
        "latest" -> None
        else     -> Some("v$version")
    }

fun newTempJekyllDoRepInstaller(): Either<String, JekyllDoRepInstaller> = try {
    val tempDir = Files.createTempDirectory("dorep-for-jekyll-ops")

    JekyllDoRepInstaller(
        tempDir, "texsydo", "dorep-for-jekyll", "0.1.0"
    ).right()
}
catch (e: IOException) {
    "Failed to create temporal directory: ${e.message}".left()
}

fun JekyllDoRepInstaller.install(): Either<String, JekyllDoRepCli> = either {
    println("⚙ Clone DoRep for Jekyll")
    println("\uD83D\uDEC8 Repo: $cloneUrl")
    println("\uD83D\uDEC8 Tag: $tag")

    runCommand(cloneCmd, parent).onLeft {
        println("❌ Fail to clone DoRep for Jekyll")
        println("\uD83D\uDEC8 $it")
    }.bind()

    println("✔ Clone DoRep for Jekyll")

    when (val t = tag) {
        is Some<String> -> {
            val (tag) = t
            println("⚙ Checkout Tag `$tag`")

            runCommand("git checkout $tag", path).onLeft {
                println("❌ Fail to checkout tag `$tag`")
                println("\uD83D\uDEC8 $it")
            }.bind()

            println("✔ Checkout Tag `$tag`")
        }

        None            -> println("- No tag to checkout — skipping")
    }

    println("⚙ Build for Jekyll")

    runCommand("./gradlew installDist", path)
        .onLeft {
            println("❌ Fail to build DoRep for Jekyll")
            println("\uD83D\uDEC8 $it")
        }
        .onRight {
            println("\uD83D\uDEC8 $it")
        }
        .bind()

    println("✔ Build DoRep for Jekyll")

    JekyllDoRepCli(this@install.binPath)
}
