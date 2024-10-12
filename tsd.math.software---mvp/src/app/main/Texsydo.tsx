// Copyright (c) 2024 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: AGPL-3.0-or-later
// This file is part of https://github.com/texsydo/texsydo---mvp

import CanvasPlay from "@app/main/tsd/canvas-play/CanvasPlay.tsx";
import Fx from "@app/main/tsd/fx/Fx.tsx";
import Text from "@app/main/tsd/text/Text.tsx";
import Web from "@app/main/tsd/web/Web.tsx";
import Header from "@components/Article/Heading/Header.tsx";
import Main from "@components/Article/Main/Main.tsx";

function Texsydo() {
    return <>
        <Main>
            <section id="tsd">
                <Header
                    appName="Texsydo"
                    icon={ {
                        src: "/texsydo.svg",
                        name: "Texsydo",
                    } }
                ></Header>

                <p>Textual System Documenting</p>
            </section>

            <Text />

            <Web />

            <Fx />

            <CanvasPlay />
        </Main>
    </>;
}

export default Texsydo;
