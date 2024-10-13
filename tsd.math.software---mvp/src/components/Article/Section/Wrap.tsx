// Copyright (c) 2024 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: AGPL-3.0-or-later
// This file is part of https://github.com/texsydo/texsydo---mvp

import { PropsWithChildren } from "react";

export const Wrap = ({ children }: PropsWithChildren) => <>
    <div className="wrap">
        { children }
    </div>
</>;
