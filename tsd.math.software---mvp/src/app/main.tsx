// Copyright (c) 2024 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: AGPL-3.0-or-later
// This file is part of https://github.com/texsydo/texsydo---mvp

import App from "@app/App.tsx";
import "@app/assets/msw-engineer.css";
import "bootstrap/dist/css/bootstrap.min.css";
import MathJaxContext from "better-react-mathjax/MathJaxContext";
import { StrictMode } from "react";
import { createRoot } from "react-dom/client";

const mathJaxConfig = {
    loader: { load: [ "input/asciimath" ] },
};

const rootEl = document.getElementById("root");

if (rootEl === null) {
    console.error("Root element not found");
}
else {
    render(rootEl);
}

function render(rootEl: HTMLElement) {
    createRoot(rootEl)
        .render(
            <StrictMode>
                <MathJaxContext config={ mathJaxConfig }>
                    <App />
                </MathJaxContext>
            </StrictMode>,
        );
}
