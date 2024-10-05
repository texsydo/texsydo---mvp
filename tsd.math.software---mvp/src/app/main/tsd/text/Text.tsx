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

                <p>Mathematical Documentation</p>

                <p>
                    Complex domains like mathematics, functional programming
                    (FP), and engineering demand clear communication but are
                    frequently challenging to present, leading to a loss
                    of funding, support, or engagement. Texsydo aims to comply
                    with MathSwe efficiency standards for formal and productive
                    communication by expanding FP to documentation domains.
                </p>

                <p>
                    Texsydo is an Applied MSW aiming
                    for <b>modern mathematical text</b>. Textual systems can
                    encompass traditional articles or specific domains. Text is
                    the <i>source code</i> that specifies an <b>article</b> of
                    formal documentation.
                </p>

                <p>
                    Texsydo <i>renders</i> text into <b>mathematical art</b> for
                    effectful domains, like a fractal from its recursive
                    definition, model visualization, or development workflow.
                </p>

                <p>
                    Texsydo engineers articles under a functional approach,
                    where the abstraction unit is the <i>article</i>, just as
                    the <i>function</i> is in FP.
                    Therefore, <b>everything is an article</b> in Texsydo.
                    Formalities and systematized articles
                    optimize <b>inference</b> to enhance mathematical and
                    engineering documentation.
                </p>

                <p>
                    Engineering functional programming into mathematical writing
                    simplifies articulating rigorous concepts that become into
                    art. While mathematical text specifies concepts, art
                    represents them, ensuring clear communication for complex
                    domains. Texsydo aims to provide mathematical text and art
                    to optimize demanding communication from mathematics to
                    engineering.
                </p>
            </div>
        </section>
    </>;
}

export default Text;
