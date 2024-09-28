// Copyright (c) 2024 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: AGPL-3.0-or-later
// This file is part of https://github.com/texsydo/texsydo---mvp

import Header from "@app/main/Header.tsx";
import "@app/main/Main.css";
import CanvasPlay from "@app/main/tsd/canvas-play/CanvasPlay.tsx";
import Fx from "@app/main/tsd/fx/Fx.tsx";
import Text from "@app/main/tsd/text/Text.tsx";
import Web from "@app/main/tsd/web/Web.tsx";

function Main() {
    return <>
        <section>
            <main>
                <article>
                    <section id="tsd">
                        <Header></Header>

                        <p>Textual System Documenting</p>
                    </section>

                    <Text />

                    <Web />

                    <Fx />

                    <CanvasPlay />
                </article>
            </main>
        </section>
    </>;
}

export default Main;
