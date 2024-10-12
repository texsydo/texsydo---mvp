// Copyright (c) 2024 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: AGPL-3.0-or-later
// This file is part of https://github.com/texsydo/texsydo---mvp

import "./Fx.css";
import Presentation from "@/components/Carousel/Presentation.tsx";
import InlineCode from "@/components/Code/InlineCode.tsx";
import SnippetBlock from "@/components/Code/SnippetBlock.tsx";
import prCoverAbstract
    from "@app/assets/cover-pr/pr-cover-_-abstract_seq-4.png";
import prCoverDetails from "@app/assets/cover-pr/pr-cover-_-details_seq-5.png";
import prCoverHeading from "@app/assets/cover-pr/pr-cover-_-heading_seq-2.png";
import prCoverProfilePhoto
    from "@app/assets/cover-pr/pr-cover-_-profile-photo_seq-1.png";
import prCoverSubdomain
    from "@app/assets/cover-pr/pr-cover-_-subdomain_seq-6.png";
import prCoverSubheading
    from "@app/assets/cover-pr/pr-cover-_-subheading_seq-3.png";
import prCoverInMathSweOps from "@app/assets/pr-cover-in-mathswe-ops.png";
import prCoverWithCustomColor from "@app/assets/pr-cover-with-custom-color.png";
import releaseCoverInMathSweLegal
    from "@app/assets/release-cover-in-mathswe-legal.png";
import fxIcon from "@app/assets/texsydo-fx.png";
import { Heading, SubHeading } from "@components/Article/Heading/Heading.tsx";

function Fx() {
    return <>
        <section className="bg-strip fx">
            <div className="bg">
                <div className="wrap wrap-full-width">
                    <div className="wrap-width">
                        <Heading
                            id="fx"
                            title="FX"
                            icon={ { src: fxIcon, name: "FX" } }
                        ></Heading>

                        <p>Text Effects</p>

                        <p>
                            Texsydo renders text as mathematical art in
                            effectful
                            domains of article graphics.
                        </p>

                        <SubHeading
                            id={ "development-article-cover" }
                            title={ "Development Article Cover" }
                        />

                        <p>Continuous Development with Documentation
                           Assistance</p>

                        <p>
                            Texsydo FX currently supports generating cover
                            images
                            for blogs that update on Pull Requests (PRs) and
                            project
                            releases in MathSwe.
                        </p>

                        <SnippetBlock caption="Generating a PR Cover Image">
                            { `
                        tsd-fx pr-cover preview \\
                        --profile-photo = profile.jpeg \\
                        --heading       = texsydo/@mvp \\
                        --subheading    = 'FX' \\
                        --abstract      = 'Shows **Textual System Documenting** with the new **Texsydo FX** prototype.' \\
                        --details       = 'Generates **PR** and **Release** covers.,Supports Markdown bold style.,Provides MVP-ready prototype.'"
                        --subdomain     = texsydo-fx---mvp.png \\
                        --bg-color      = white \\
                        --bg            = msw-fractal.png
                        ` }
                        </SnippetBlock>

                        <p>
                            The above command will show a preview window with
                            the
                            rendered image.
                        </p>

                        <p>
                            FX will save the image by removing
                            the <InlineCode>preview</InlineCode> unnamed
                            parameter
                            and specifying
                            an <InlineCode>output</InlineCode> argument with the
                            destination path.
                        </p>
                    </div>

                    <div className="wrap-graphics">
                        <Presentation
                            id="texsydo-fx-_-pr-cover"
                            title="PR Cover"
                            sequence={ [
                                {
                                    src: prCoverProfilePhoto,
                                    title: "Profile Photo",
                                },
                                {
                                    src: prCoverHeading,
                                    title: "Heading",
                                },
                                {
                                    src: prCoverSubheading,
                                    title: "Subheading",
                                },
                                {
                                    src: prCoverAbstract,
                                    title: "Abstract",
                                },
                                {
                                    src: prCoverDetails,
                                    title: "Details",
                                },
                                {
                                    src: prCoverSubdomain,
                                    title: "Subdomain",
                                },
                            ] }
                        />

                        <div className="images">
                            <div className="row">
                                <figure>
                                    <img
                                        src={ prCoverWithCustomColor }
                                        alt="PR Cover with Custom Color"
                                    />
                                    <figcaption>
                                        PR Cover with Custom Color
                                    </figcaption>
                                </figure>

                                <figure>
                                    <img
                                        src={ prCoverInMathSweOps }
                                        alt="PR Cover in MathSwe Ops"
                                    />
                                    <figcaption>
                                        PR Cover in MathSwe Ops
                                    </figcaption>
                                </figure>
                            </div>

                            <div className="row">
                                <figure>
                                    <img
                                        src={ releaseCoverInMathSweLegal }
                                        alt="Release Cover in MathSwe Legal"
                                    />
                                    <figcaption>
                                        Release Cover in MathSwe Legal
                                    </figcaption>
                                </figure>
                            </div>
                        </div>
                    </div>

                    <div className="wrap-width">
                        <p>
                            Release covers take the profile photo of the
                            organization rather than the developer or team.
                        </p>

                        <p>
                            Further, they support versions for micro-projects
                            besides the version of the whole repository.
                            Standalone projects of a common repository, like <b>
                            microservices and MVPs, integrate smoothly</b> with
                            their
                            independent version.
                        </p>

                        <p>
                            Texsydo Web, with domain specifications, infers
                            development context from PRs and releases and calls
                            FX
                            to streamline article graphics under MathSwe
                            standards.
                        </p>
                    </div>
                </div>
            </div>
        </section>
    </>;
}

export default Fx;
