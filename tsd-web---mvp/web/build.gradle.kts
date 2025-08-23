// Copyright (c) 2023-2025 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: AGPL-3.0-or-later
// This file is part of https://github.com/texsydo/texsydo---mvp

plugins {
    alias(libs.plugins.kotlin.jvm)

    `java-library`
}

dependencies {
    implementation(libs.arrow.core)
    implementation(libs.arrow.fx.coroutines)

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")

    testImplementation(libs.junit.jupiter.engine)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
