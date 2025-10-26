<!-- Copyright (c) 2025 Tobias Briones. All rights reserved. -->
<!-- SPDX-License-Identifier: AGPL-3.0-or-later -->
<!-- This file is part of https://github.com/texsydo/texsydo---mvp -->

# Texsydo Web MVP: Web Module

The web module defines Texsydo articles and provides the tooling to convert them
into web artifacts, like static websites.

## Text to Web

The Text to Web feature is the simplest article form, where the source is a
plain text file, and Texsydo Web builds a static website containing the webpage
version of the text file.

```kotlin
val blogRoot = Path("tsd-blog")
val articleRelPath = Path("abstract-text.txt")
val entry = Entry(blogRoot, articleRelPath)

fun run() = either {
    jekyllArticleOpsFrom(entry)
        .loadIndex().bind()
        .buildEnvironment().bind()
        .installTempJekyllDoRepCli().bind()
        .build()
}
```

The program output will be like the following.

```
⚙ Build Jekyll environment
🛈 Source: file:////home/user/texsydo/texsydo---mvp/tsd-web---mvp/web/docs/integration/tsd-blog
🛈 Destination: file:////tmp/build---dorep-for-jekyll---web---tsd-web---mvp4834793869714177189
🛈 Markdown index copied to file:////tmp/build---dorep-for-jekyll---web---tsd-web---mvp4834793869714177189/abstract-text/index.md
✔️ Copy Jekyll Meta
✔️ Copy Article Navigation
✔️ Copy Article TOC
✅ Build Jekyll environment
⚙ Clone DoRep for Jekyll
🛈 Repo: https://github.com/texsydo/dorep-for-jekyll.git
🛈 Tag: Option.Some(v0.1.0)
✔ Clone DoRep for Jekyll
⚙ Checkout Tag `v0.1.0`
✔ Checkout Tag `v0.1.0`
⚙ Build for Jekyll
🛈 > Task :checkKotlinGradlePluginConfigurationErrors SKIPPED
> Task :processResources NO-SOURCE

> Task :compileKotlin

> Task :compileJava NO-SOURCE
> Task :classes UP-TO-DATE
> Task :jar
> Task :startScripts
> Task :installDist

BUILD SUCCESSFUL in 2s
4 actionable tasks: 4 executed
Consider enabling configuration cache to speed up this build: https://docs.gradle.org/9.0.0/userguide/configuration_cache_enabling.html

✔ Build DoRep for Jekyll
⚙ Jekyll Build
✔️ Create temporal build directory
⚙ Build Article
🛈 Source: /tmp/build---dorep-for-jekyll---web---tsd-web---mvp4834793869714177189
🛈 Destination: /tmp/dorep-for-jekyll---web---tsd-web---mvp15296954748666731099
✔ Build Article
🛈 Article built at file:////tmp/dorep-for-jekyll---web---tsd-web---mvp15296954748666731099/_site
✅ Jekyll Build
```

For more details, see [integration](docs/integration).

## Project Ops

- `./gradlew clean`
- `./gradlew test`
- `./gradlew build`
