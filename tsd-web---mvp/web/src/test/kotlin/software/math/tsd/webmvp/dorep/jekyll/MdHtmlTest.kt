// Copyright (c) 2023-2025 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: AGPL-3.0-or-later
// This file is part of https://github.com/texsydo/texsydo---mvp

package software.math.tsd.webmvp.dorep.jekyll

import TestResources
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import software.math.tsd.webmvp.md.MarkdownContent
import java.nio.file.Path
import kotlin.io.path.readText

class MdHtmlTest : StringSpec({
    val index = MarkdownContent(
        TestResources
            .pathOf(Path.of("fp-in-kotlin", "index.md"))
            .readText()
    )

    "navHtml should generate expected navigation HTML" {
        val navHtml = index.generateNav("Blog").toHtmlString()
        val expected = """
            <nav aria-label="Navigation">
              <a class="home" href="/">
                <span class="material-symbols-rounded">
                  home
                </span>
                <span>
                  Blog
                </span>
              </a>
            </nav>
        """.trimIndent()

        navHtml shouldBe expected
    }

    "tocHtml should generate expected table of contents HTML" {
        val tocHtml = index.generateToC().toHtmlString()
        val expected = """
              <nav class="toc user-select-none" aria-label="Table of Contents">
                <a class="title" href="#">
                  FP in Kotlin
                </a>
                <ul>
                  <li>
                    <a href="#pipe-operator">
                      Pipe Operator
                    </a>
                    <ul>
                      <li>
                        <a href="#defining-a-pipe-operator">
                          Defining a Pipe Operator
                        </a>
                        <ul>
                          <li>
                            <a href="#finding-a-suitable-symbol">
                              Finding a Suitable Symbol
                            </a>
                          </li>
                          <li>
                            <a href="#language-features">
                              Language Features
                            </a>
                          </li>
                          <li>
                            <a href="#operator-definition">
                              Operator Definition
                            </a>
                          </li>
                          <li>
                            <a href="#usage-example">
                              Usage Example
                            </a>
                          </li>
                          <li>
                            <a href="#functional-language-design">
                              Functional Language Design
                            </a>
                          </li>
                          <li>
                            <a href="#custom-pipe-in-kotlin">
                              Custom Pipe in Kotlin
                            </a>
                          </li>
                        </ul>
                      </li>
                      <li>
                        <a href="#options-for-a-pipe-operator-in-kotlin">
                          Options for a Pipe Operator in Kotlin
                        </a>
                      </li>
                    </ul>
                  </li>
                  <li>
                    <a href="#designing-functional-languages-in-kotlin">
                      Designing Functional Languages in Kotlin
                    </a>
                  </li>
                </ul>
              </nav>
        """.trimIndent()

        tocHtml shouldBe expected
    }
})
