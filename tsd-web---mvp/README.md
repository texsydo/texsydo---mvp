<!-- Copyright (c) 2025 Tobias Briones. All rights reserved. -->
<!-- SPDX-License-Identifier: AGPL-3.0-or-later -->
<!-- This file is part of https://github.com/texsydo/texsydo---mvp -->

# MVP: Texsydo Web

[![Project](https://mathswe-ops-services.tobiasbriones-dev.workers.dev/badge/project/texsydo)](https://tsd.math.software)
&nbsp;
[![GitHub Repository](https://img.shields.io/static/v1?label=GITHUB&message=REPOSITORY&labelColor=555&color=0277bd&style=for-the-badge&logo=GITHUB)](https://github.com/texsydo/texsydo---mvp/edit/main/tsd-web---mvp)

[![GitHub Project License](https://img.shields.io/github/license/texsydo/texsydo---mvp.svg?style=flat-square)](https://github.com/texsydo/texsydo---mvp/blob/main/LICENSE)

[![GitHub Release](https://mathswe-ops-services.tobiasbriones-dev.workers.dev/badge/version/github/texsydo/texsydo---mvp?path=tsd-web---mvp)](https://github.com/texsydo/texsydo---mvp/releases/latest)

## Running the App

Run `gradlew run` to run the CLI app.

Run with arguments: `gradlew run --args="{ args }"`.

### Running Commands

#### tsd-web---mvp entries

```
gradlew run --args="entries"
```

#### tsd-web---mvp build pi-day jekyll

```
gradlew run --args="build pi-day jekyll"
```

See
[Automating the Platform Operations and Beyond (2023/08/31)](https://blog.mathsoftware.engineer/automating-the-platform-operations-and-beyond-2023-08-31)
for the original prototype commands and documentation.

## Testing

Run `gradlew test` to run all tests. It doesn't log testing information if all
tests passed.

## Project

### Updating Gradle

Run `gradlew wrapper --gradle-version latest` to set the Gradle version to the
latest in `gradle-wrapper.properties`.
