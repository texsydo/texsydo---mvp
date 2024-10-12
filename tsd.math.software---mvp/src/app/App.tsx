// Copyright (c) 2024 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: AGPL-3.0-or-later
// This file is part of https://github.com/texsydo/texsydo---mvp

import "@app/App.css";
import Footer from "@app/Footer.tsx";
import Texsydo from "@app/main/Texsydo.tsx";

function App() {
    return <>
        <Texsydo></Texsydo>

        <Footer></Footer>
    </>;
}

export default App;
