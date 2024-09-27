// Copyright (c) 2024 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: AGPL-3.0-or-later
// This file is part of https://github.com/texsydo/texsydo---mvp

import "./Web.css";
import webIcon from "@app/assets/texsydo-web.svg";
import { Heading } from "@app/main/Heading.tsx";
import TerminalOutput from "@app/main/tsd/components/TerminalOutput.tsx";
import { faTerminal } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { SubHeading } from "../../Heading.tsx";

interface InlineCodeProps {
    children: string;
}

function InlineCode({ children }: InlineCodeProps) {
    return <>
        <code className="code language-plaintext highlighter-rouge">
            { children }
        </code>
    </>;
}

interface CommandProps {
    command: string;
    caption: string;
}

function Command({ command, caption }: CommandProps) {
    return <>
        <figure>
            <div>
                <code className="d-block language-plaintext highlighter-rouge">
                    <FontAwesomeIcon
                        className="mx-2"
                        icon={ faTerminal }
                        color="#fafafa"
                        style={ {
                            top: "2px",
                            background: "#212121",
                            padding: "2px 4px",
                            borderRadius: "4px",
                        } }
                        size="xs"
                    />
                    { command }
                </code>
            </div>

            <figcaption>{ caption }</figcaption>
        </figure>
    </>;
}

interface CommandBlockProps {
    children: string;
    caption: string;
}

function SnippetBlock({ children, caption }: CommandBlockProps) {
    return <>
        <figure>
            <div>
                <pre>
                    <code className="d-block language-plaintext highlighter-rouge">
                        { children
                            .trim()
                            .split("\n")
                            .map(line => line.trim())
                            .join("\n") }
                    </code>
                </pre>
            </div>

            <figcaption>{ caption }</figcaption>
        </figure>
    </>;
}

function Web() {
    return <>
        <section className="bg-strip web">
            <div className="bg">
                <div className="wrap">
                    <Heading
                        id="web"
                        title="Web"
                        icon={ webIcon }
                    ></Heading>

                    <p>Mathematical Text to Web Article</p>

                    <p>
                        Texsydo Web models mathematical articles as source code
                        of a domain-specific system, which transforms into web
                        articles adhering to MathSwe standards.
                    </p>

                    <p>
                        Web articles are modern, portable, easy to write, and
                        deploy with minimal overhead or setup.
                    </p>

                    <SubHeading id="web-ops" title="Web Ops" />

                    <p>Texsydo Web Operations</p>

                    <Command
                        command="tsd-web create { entry-id } { class_1, class_2, ..., class_n }"
                        caption="New Article"
                    />

                    <p>
                        For example:
                        <div>
                            Entry ID
                            = <InlineCode>automating-the-platform-operations-and-beyond-2023-08-31</InlineCode>
                        </div>
                        <div>
                            Classes
                            = <InlineCode>mathswe, eng, automation, platform,
                                          ops</InlineCode>
                        </div>
                    </p>

                    <TerminalOutput
                        outputs={ [
                            "Checkout to branch main",
                            "Update branch main",
                            "Checkout to branch automating-the-platform-operations-and-beyond-2023-08-31",
                            "Add files to Git",
                            "Commit files to Git",
                            "Create entry Automating the Platform Operations and Beyond (2023/08/31)",
                        ] }
                    />

                    <SnippetBlock caption="New Entry Example Tree">
                        { `
                        .
                        ├── mathswe
                        │   ├── eng
                        │       ├── automation
                        │           └── platform
                        │               └── ops
                        │                   └── automating-the-platform-operations-and-beyond-2023-08-31
                        │                       └── index.md
                        ` }
                    </SnippetBlock>
                </div>
            </div>
        </section>
    </>;
}

export default Web;
