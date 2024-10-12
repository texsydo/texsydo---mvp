// Copyright (c) 2024 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: AGPL-3.0-or-later
// This file is part of https://github.com/texsydo/texsydo---mvp

import { PropsWithChildren } from "react";

type Wrap = "wrap" | "none"

type SectionProps = PropsWithChildren & {
    className: string,
    wrap?: Wrap,
}

export const Section = ({ children, className, wrap }: SectionProps) => {
    const wrapped = () => <>
        <div className="wrap">
            { children }
        </div>
    </>;

    const noWrap = () => <>{ children }</>;

    return <>
        <section className={ className }>
            { wrap === "none" ? noWrap() : wrapped() }
        </section>
    </>;
};
