// Copyright (c) 2024 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: AGPL-3.0-or-later
// This file is part of https://github.com/texsydo/texsydo---mvp

import "./SnippetBlock.css"

type SnippetBlockProps = {
    children: string;
    caption: string;
}

function SnippetBlock({ children, caption }: SnippetBlockProps) {
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

export default SnippetBlock;
