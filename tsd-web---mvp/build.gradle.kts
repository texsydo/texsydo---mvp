// Copyright (c) 2023-2025 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: AGPL-3.0-or-later
// This file is part of https://github.com/texsydo/texsydo---mvp

import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "2.2.0"
    id("org.beryx.runtime") version "1.13.1"
}

group = "software.math.tsd"
version = "0.1.0-dev"

application {
    mainClass = "software.math.tsd.webmvp.TexsydoWebKt"
}

kotlin {
    jvmToolchain(21)
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_21)
    }
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = application.mainClass.get()
    }
}

distributions {
    main {
        contents {
            from(rootDir.resolve("src/main")) {
                include("files/**")
            }
        }
    }
}

runtime {
    modules.set(listOf("java.base"))

    jpackage {
        // It requires the package `rpm` in Linux and Wix 3 on Windows
        // https://docs.oracle.com/en/java/javase/14/jpackage/packaging-overview.html

        val currentOs = org.gradle.internal.os.OperatingSystem.current()

        if (currentOs.isLinux) {
            val installerTypeProperty = project
                .findProperty("installerType") as String?

            // Set the installer type (DEB) explicitly from the argument to
            // avoid building RMP (building RMP on Ubuntu might fail
            // sometimes)
            if (installerTypeProperty != null) {
                installerType = installerTypeProperty
            }

            // For Debian. Overrides resources (untested on RedHat)
            installerOptions.addAll(
                listOf(
                    "--resource-dir",
                    "jpackage/linux",
                    "--verbose",
                )
            )
        }
        else if (currentOs.isWindows) {
            imageOptions.addAll(listOf("--win-console"))

            installerOptions.addAll(
                listOf(
                    "--resource-dir",
                    "jpackage/windows",
                    "--verbose",
                    "--win-per-user-install",
                    "--win-dir-chooser",
                    "--win-menu",
                )
            )
        }
    }
}

