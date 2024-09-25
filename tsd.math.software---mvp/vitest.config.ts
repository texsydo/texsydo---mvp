// Copyright (c) 2024 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: AGPL-3.0-or-later
// This file is part of https://github.com/texsydo/texsydo---mvp

import { defineConfig } from "vitest/config";
import viteConfig from "./vite.config.ts";

export default defineConfig({
    ...viteConfig,
    test: {
        globals: true,
        environment: "jsdom",
        setupFiles: [ "./src/setupTests.ts" ],
    },
    esbuild: {
        jsxInject: `import React from 'react'`,  // Automatically inject React
    },
});
