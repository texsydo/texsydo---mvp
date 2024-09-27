// Copyright (c) 2024 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: AGPL-3.0-or-later
// This file is part of https://github.com/texsydo/texsydo---mvp

import "./TerminalOutput.css";
import {
    isItemBlank,
    itemToCssClass,
    itemToIcon,
    itemToString,
    TerminalItem,
} from "./TerminalItem.ts";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

interface TerminalOutputProps {
    outputs: TerminalItem[];
}

function TerminalOutput({ outputs }: TerminalOutputProps) {
    const maxLength = 60;
    const normalize = (item: TerminalItem) => {
        const output = itemToString(item);

        return output.length > maxLength
               ? output.substring(0, maxLength) + "..."
               : output;
    };

    const emptyItem = (index: number) => <>
        <div key={ index } className="item blank"></div>
    </>;

    const newItem = (
        output: TerminalItem,
        index: number,
    ) => <>
        <div key={ index } className="item">
            <FontAwesomeIcon
                className={ itemToCssClass(output) }
                icon={ itemToIcon(output) }
            ></FontAwesomeIcon>

            <span>{ normalize(output) }</span>
        </div>
    </>;

    const outputToItem = (
        output: TerminalItem,
        index: number,
    ) =>
        isItemBlank(output)
        ? emptyItem(index)
        : newItem(output, index);

    return <>
        <div className="terminal-output">
            <div className="content">
                { outputs.map(outputToItem) }
            </div>
        </div>
    </>;
}

export default TerminalOutput;
