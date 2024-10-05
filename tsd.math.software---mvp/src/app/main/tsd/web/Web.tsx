// Copyright (c) 2024 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: AGPL-3.0-or-later
// This file is part of https://github.com/texsydo/texsydo---mvp

import "./Web.css";
import webIcon from "@app/assets/texsydo-web.svg";
import { Heading, SubSubHeading } from "@app/main/Heading.tsx";
import TerminalOutput
    from "@app/main/tsd/components/TerminalOutput/TerminalOutput.tsx";
import { faTerminal } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { MathJax } from "better-react-mathjax";
import { Fragment, ReactNode } from "react";
import { SubHeading } from "../../Heading.tsx";

type InlineCodeProps = {
    children: ReactNode;
    backgroundColor?: string;
}

function InlineCode({ children, backgroundColor }: InlineCodeProps) {
    return <>
        <code
            className="code language-plaintext highlighter-rouge"
            style={ { backgroundColor } }
        >
            { children }
        </code>
    </>;
}

type InlineCodeMapProps = {
    maps: Record<string, string>
}

function InlineCodeMap({ maps }: InlineCodeMapProps) {
    const entries = Object.entries(maps);

    const newItem = ([ from, to ]: string[], index: number) => (
        <Fragment key={ index }>
            <div className="grid-item">
                <InlineCode>{ from }</InlineCode>
            </div>

            <div className="grid-item">
                <MathJax>{ "`\\to`" }</MathJax>
            </div>

            <div className="grid-item">
                <InlineCode backgroundColor="#BBDEFB">{ to }</InlineCode>
            </div>
        </Fragment>
    );

    return <>
        <div className="pb-2 word-map-container">
            { entries.map(newItem) }
        </div>
    </>;
}

type CommandProps = {
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

type CommandBlockProps = {
    children: string;
    caption: string;
}

function SnippetBlock({ children, caption }: CommandBlockProps) {
    return <>
        <figure className="snippet-block compact">
            <div>
                <pre>
                    <code className="d-block language-plaintext highlighter-rouge">
                        { children
                            .trim()
                            .split("\n")
                            .map(line => line.trim().replace(/^\|/, " "))
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
                        Web articles are modern, portable, easy to write, and
                        deploy with minimal overhead or setup.
                    </p>

                    <SubHeading id="web-ops" title="Web Ops" />

                    <p>Texsydo Web Operations</p>

                    <p>
                        Operations provide commands to create, build, and deploy
                        mathematical articles in your domain-specific system.
                    </p>

                    <p>
                        ⚠️ Texsydo Web is a challenging project, so its current
                        MVP versions do not guarantee API stability for now.
                    </p>

                    <SubSubHeading id="new-article" title="New Article" />

                    <p>Creating Domain Aware Articles</p>

                    <Command
                        command="tsd-web create { entry-id } { class_1, class_2, ..., class_n }"
                        caption="New Article"
                    />

                    <div className="text-center mb-4">
                        <strong>Create Article Example</strong>

                        <ul className="text-start">
                            <li>
                                Entry ID
                                is <InlineCode>automating-the-platform-operations-and-beyond-2023-08-31</InlineCode>.
                            </li>
                            <li>
                                Classes are
                                <InlineCode>
                                    mathswe, eng, automation, platform, ops
                                </InlineCode>.
                            </li>
                        </ul>
                    </div>

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
                        |   ├── eng
                        |       ├── automation
                        |           └── platform
                        |               └── ops
                        |                   └── automating-the-platform-operations-and-beyond-2023-08-31
                        |                       └── index.md
                        ` }
                    </SnippetBlock>

                    <SubSubHeading
                        id="listing-articles"
                        title="Listing Articles"
                    />

                    <p>Repository Articles</p>

                    <Command
                        command="tsd-web entries"
                        caption="Entires Command"
                    />

                    <SubSubHeading
                        id="building-an-article"
                        title="Building an Article"
                    />

                    <p>Markdown to Static Site</p>

                    <Command
                        command="tsd-web build { target } (jekyll)"
                        caption="Build Command"
                    />

                    <p>
                        ⚠️ Texsydo Web MVP will drop support for Jekyll later
                        when it reaches a more mature stage.
                    </p>

                    <div className="text-center mb-4">
                        <strong>Common Build Usage</strong>

                        <ul className="text-start">
                            <li>
                                Target is the current
                                branch <InlineCode>.</InlineCode>.
                            </li>

                            <li>
                                The <InlineCode>jekyll</InlineCode> flag builds
                                intermediate code to a static site.
                            </li>

                            <li>
                                Run <InlineCode>tsd-web build .
                                                jekyll</InlineCode>.
                            </li>
                        </ul>
                    </div>

                    <SubSubHeading
                        id="serving-an-article"
                        title="Serving an Article"
                    />

                    <p>Development Server</p>

                    <Command
                        command="tsd-web serve"
                        caption="Serve Command"
                    />

                    <SubSubHeading
                        id="deploying-an-article"
                        title="Deploying an Article"
                    />

                    <p>GitHub Pages Deployment</p>

                    <Command
                        command="tsd-web deploy { entry-id }"
                        caption="Deploy Command"
                    />

                    <TerminalOutput
                        outputs={ [
                            { icon: "gear", value: "Deploying" },
                            "Checkout to branch main",
                            "Update branch main",
                            "",
                            { icon: "gear", value: "Building" },
                            "Build article automating-the-platform-operations-and-beyond-2023-08-31",
                            "Checkout to branch gh-pages",
                            "Update branch gh-pages",
                            "Commiting build of automating-the-platform-operations-and-beyond-2023-08-31",
                            "Add files to Git",
                            "Commit files to Git",
                            "",
                            { icon: "gear", value: "Publishing" },
                            "Push branch gh-pages to origin",
                            "Checkout to branch main",
                            "Deploy automating-the-platform-operations-and-beyond-2023-08-31",
                        ] }
                    />

                    <div className="text-center">
                        <p><strong>Production Article</strong></p>

                        <a
                            rel="noreferrer"
                            target="_blank"
                            href="https://blog.mathsoftware.engineer/automating-the-platform-operations-and-beyond-2023-08-31"
                        >
                            Automating the Platform Operations and Beyond
                            (2023/08/31)
                        </a>
                    </div>

                    <p>
                        It deploys the given article from the main branch to the
                        gh-pages one.
                    </p>

                    <SubHeading
                        id="domain-dictionary"
                        title="Domain Dictionary"
                    />

                    <p>Smart Text Transformations</p>

                    <p>
                        Declarative user dictionaries tell Texsydo the
                        transformations from the low-level form of a word used
                        as a syntax to the high-level word to render.
                    </p>

                    <p>
                        Dictionaries keep the system domain-specific and,
                        therefore, well-informed and efficient by removing
                        redundant work prone to errors and writer burnout.
                    </p>

                    <SnippetBlock caption="Custom Dictionary Example">
                        { `
                        Dictionary(
                        |   uppercase = setOf("bsd", "pr"),
                        |   composed = mapOf(
                        |       "mathswe" to listOf("math", "swe"),
                        |       "github"  to listOf("git", "hub"),
                        |   ),
                        |   acronym = mapOf(
                        |       "swam" to "Special Software and Models",
                        |       "ddo"  to "Data-Driven Organizations",
                        |   ),
                        |   custom = mapOf(
                        |       "intellij-idea" to "IntelliJ IDEA",
                        |       "leds"          to "LEDs",
                        |   )
                        )
                        ` }
                    </SnippetBlock>

                    <p><strong>Map of Words</strong></p>

                    <ul className="flex-list">
                        <li>
                            <InlineCodeMap
                                maps={ {
                                    bsd: "BSD",
                                    pr: "PR",
                                } }
                            />
                        </li>

                        <li>
                            <InlineCodeMap
                                maps={ {
                                    mathswe: "MathSwe",
                                    github: "GitHub",
                                } }
                            />
                        </li>

                        <li>
                            <InlineCodeMap
                                maps={ {
                                    swam: "Special Software and Models",
                                    ddo: "Data-Driven Organizations",
                                } }
                            />
                        </li>

                        <li>
                            <InlineCodeMap
                                maps={ {
                                    "intellij-idea": "IntelliJ IDEA",
                                    leds: "LEDs",
                                } }
                            />
                        </li>
                    </ul>
                </div>
            </div>
        </section>
    </>;
}

export default Web;
