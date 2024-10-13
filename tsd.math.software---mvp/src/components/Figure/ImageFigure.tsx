// Copyright (c) 2024 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: AGPL-3.0-or-later
// This file is part of https://github.com/texsydo/texsydo---mvp

type ImageFigureProps = {
    src: string,
    caption: string,
}

export const ImageFigure = ({ src, caption }: ImageFigureProps) => <>
    <figure>
        <img src={ src } alt={ caption } />
        <figcaption>{ caption }</figcaption>
    </figure>
</>;
