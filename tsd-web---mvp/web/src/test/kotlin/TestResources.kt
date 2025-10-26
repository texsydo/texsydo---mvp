// Copyright (c) 2023-2025 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: AGPL-3.0-or-later
// This file is part of https://github.com/texsydo/texsydo---mvp

import java.nio.file.Path

class TestResources {
    companion object {
        fun pathOf(resourcePath: Path): Path = Path
            .of("", "src", "test", "resources", resourcePath.toString())
            .toAbsolutePath()
    }
}
