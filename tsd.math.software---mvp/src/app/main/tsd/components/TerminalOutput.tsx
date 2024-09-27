// Copyright (c) 2024 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: AGPL-3.0-or-later
// This file is part of https://github.com/texsydo/texsydo---mvp

import "./TerminalOutput.css";
import { faCheck } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

interface TerminalOutputProps {
    outputs: string[];
}

function TerminalOutput({ outputs }: TerminalOutputProps) {
    const maxLength = 60;
    const normalize = (output: string) =>
        output.length > maxLength
        ? output.substring(0, maxLength) + "..."
        : output;

    const outputToItem = (output: string) => <>
        <div className="item">
            <FontAwesomeIcon icon={ faCheck }></FontAwesomeIcon>
            <span>{ normalize(output) }</span>
        </div>
    </>;

    return <>
        <div className="terminal-output">
            <div className="content">
                { outputs.map(outputToItem) }
            </div>
        </div>
    </>;
}

export default TerminalOutput;
