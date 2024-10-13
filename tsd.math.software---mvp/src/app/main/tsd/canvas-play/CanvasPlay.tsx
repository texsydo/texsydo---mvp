// Copyright (c) 2024 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: AGPL-3.0-or-later
// This file is part of https://github.com/texsydo/texsydo---mvp

import "./CanvasPlay.css";
import birdCat from "@app/assets/anim/bird-cat_1800-60fps.mp4";
import flower from "@app/assets/anim/drawing-a-flower_1800-60fps.mp4";
import renderingOrder from "@app/assets/anim/rendering-order_fhd-60fps.mp4";
import repsymo from "@app/assets/anim/repsymo_1s-fhd-60fps.mp4";
import canvasPlayIcon from "@app/assets/canvas-play.png";
import { Heading, SubHeading } from "@components/Article/Heading/Heading.tsx";
import { Section } from "@components/Article/Section/Section.tsx";
import { Wrap } from "@components/Article/Section/Wrap.tsx";
import InlineCode from "@components/Code/InlineCode.tsx";
import AspectAnim from "@components/VideoAnim/AspectAnim.tsx";
import { faTerminal } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

type PromptProps = {
    prompt: string
}

function Prompt({ prompt }: PromptProps) {
    return <>
        <code className="prompt">
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
            { prompt }
        </code>
    </>;
}

function CanvasPlay() {
    return <>
        <Section bg className="bg-strip canvas-play" wrap="none">
            <Wrap>
                <Heading
                    id="canvas-play"
                    title="Canvas Play"
                    icon={ {
                        src: canvasPlayIcon,
                        name: "Canvas Play",
                    } }
                ></Heading>

                <p>Innovating Incoming Visualizations</p>

                <p>
                    Texsydo FX has internal prototypes to design new graphical
                    products beyond Texsydo to iterate results earlier.
                </p>
            </Wrap>

            <Wrap>
                <SubHeading id="repsymo-fx" title="Repsymo FX" />

                <p>Model Representation</p>

                <p>
                    Texsydo FX Prototype currently provides support to
                    render model visualizations of Repsymo.
                </p>

                <p>
                    Repsymo is an Applied MSW that currently
                    solves <strong>Operations Research (OR)</strong> models,
                    while Texsydo FX Prototype powers their rendering.
                </p>

                <div className="text-center mt-5">
                    <strong>Machine Replacement Model</strong>
                </div>

                <Prompt prompt="Text" />

                <p>
                    The initial machine age is three years, with a maximum
                    operational lifespan of six years. A new machine costs
                    $100,000.
                </p>

                <Prompt prompt="Solution Space" />

                <div className="my-2">
                    Where:
                </div>

                <ul>
                    <li>
                        X-axis is the <InlineCode>decisionYear</InlineCode>.
                    </li>
                    <li>
                        Y-axis is the <InlineCode>machineAge</InlineCode>.
                    </li>
                </ul>

                <Prompt prompt="Step-by-Step Rendering" />
            </Wrap>

            <AspectAnim
                sources={ [ renderingOrder, repsymo ] }
                ratio={ "a9-16" }
                maxHeight="max-80vh-when-not-full-width"
            />

            <Wrap>
                <p>
                    The above animations are <b>FHD 9:16 60FPS</b> renderings,
                    and the time per slide is also configurable.
                </p>

                <p>
                    The first visualization showcases the step-by-step
                    general rendering order of the solutions tree, while the
                    second offers a complete visualization of all the rendering
                    steps and <strong>model solutions</strong>.
                </p>

                <p>
                    Texsydo FX can implement new graphical features quickly to
                    pipe the text or mathematical output of another MSW,
                    like Repsymo, and render it into formal visualizations.
                </p>
            </Wrap>

            <Wrap>
                <SubHeading
                    id="engineering-specs-and-art"
                    title="Engineering Specs and Art"
                />

                <p>DSLs for Domain Experts</p>

                <p>
                    The Texsydo FX Prototype is currently experimenting with new
                    graphics APIs based on evolving requirements.
                </p>

                <p>
                    APIs can provide a language to final users,
                    allowing them
                    to <strong>document specifications</strong> with domain
                    tools like rulers, captions, etc. Further, it enables
                    the <strong>creation of art from text</strong> using
                    only mathematical definitions, like shapes and equations.
                </p>
            </Wrap>

            <AspectAnim
                sources={ [ flower, birdCat ] }
                ratio={ "a1-1" }
                maxHeight="max-80vh-when-not-full-width"
            />

            <Wrap>
                <p>
                    The above animations are <b>1800p 1:1 60FPS</b> renderings.
                </p>

                <p>
                    The first animation represents a drawing with
                    metrics, while the second builds an artistic drawing with
                    shapes, both step-by-step.
                </p>

                <p>
                    Texsydo FX aims to provide DSLs for domain experts to create
                    formal specifications and mathematical art while its
                    prototypes allow quicker iterations to speed up innovation.
                </p>
            </Wrap>
        </Section>
    </>;
}

export default CanvasPlay;
