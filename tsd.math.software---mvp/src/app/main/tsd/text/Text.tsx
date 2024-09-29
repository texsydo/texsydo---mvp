// Copyright (c) 2024 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: AGPL-3.0-or-later
// This file is part of https://github.com/texsydo/texsydo---mvp

import "./Text.css";
import textIcon from "@app/assets/text.svg";
import { Heading } from "@app/main/Heading.tsx";

function Text() {
    return <>
        <section className="text">
            <div className="wrap">
                <Heading
                    id="text"
                    title="Text"
                    icon={ textIcon }
                ></Heading>
            </div>
        </section>
    </>;
}

export default Text;
