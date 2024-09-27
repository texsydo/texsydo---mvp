// Copyright (c) 2024 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: AGPL-3.0-or-later
// This file is part of https://github.com/texsydo/texsydo---mvp

import "./TerminalOutput.css";
import {
    faCheck,
    faGear,
    IconDefinition,
} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

type TerminalIcon = "check" | "gear";

const classFromIcon = (icon: TerminalIcon) => ({
    check: "check-icon",
    gear: "gear-icon",
}[icon]);

const faIcon = (icon: TerminalIcon): IconDefinition => ({
    check: faCheck,
    gear: faGear,
}[icon]);

type TerminalItem = string | { icon: TerminalIcon, value: string };

const itemToString = (item: TerminalItem): string =>
    typeof item === "string"
    ? item
    : item.value;

const itemToIcon = (item: TerminalItem): IconDefinition =>
    typeof item === "string"
    ? faCheck
    : faIcon(item.icon);

const itemToCssClass = (item: TerminalItem): string =>
    typeof item === "string"
    ? "check-icon"
    : classFromIcon(item.icon);

const isItemBlank = (item: TerminalItem): boolean =>
    itemToString(item).trim().length === 0;

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

    const emptyItem = <>
        <div className="item blank"></div>
    </>;

    const newItem = (output: TerminalItem) => <>
        <div className="item">
            <FontAwesomeIcon
                className={ itemToCssClass(output) }
                icon={ itemToIcon(output) }
            ></FontAwesomeIcon>

            <span>{ normalize(output) }</span>
        </div>
    </>;

    const outputToItem = (output: TerminalItem) =>
        isItemBlank(output)
        ? emptyItem
        : newItem(output);

    return <>
        <div className="terminal-output">
            <div className="content">
                { outputs.map(outputToItem) }
            </div>
        </div>
    </>;
}

export default TerminalOutput;
