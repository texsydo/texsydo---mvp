// Copyright (c) 2024 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: AGPL-3.0-or-later
// This file is part of https://github.com/texsydo/texsydo---mvp

import App from "@app/App.tsx";
import { render, screen } from "@testing-library/react";

describe("App tests", () => {
    it("should render the title", () => {
        render(<App />);

        expect(
            screen.getByRole("heading", {
                level: 1,
            }),
        ).toHaveTextContent("Texsydo");
    });
});
