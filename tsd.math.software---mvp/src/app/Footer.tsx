// Copyright (c) 2024 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: AGPL-3.0-or-later
// This file is part of https://github.com/texsydo/texsydo---mvp

import { APP_VERSION_LABEL } from "@/info.tsx";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faLinkedin } from "@fortawesome/free-brands-svg-icons/faLinkedin";
import { faGithub } from "@fortawesome/free-brands-svg-icons/faGithub";

function Footer() {
    return <>
        <footer>
            <div className="nav row">
                <section className="col-6">
                    <h5>
                        Math Software
                    </h5>
                    <ul>
                        <li>
                            <a href="https://math.software">Math</a>
                        </li>
                        <li>
                            <a href="https://tsd.math.software">Texsydo</a>
                        </li>
                        <li>
                            <a href="https://rsm.math.software">Repsymo</a>
                        </li>
                        <li>
                            <a href="https://ops.math.software">Ops</a>
                        </li>
                    </ul>
                </section>

                <section className="col-6">
                    <h5>
                        MSW Engineer
                    </h5>

                    <ul>
                        <li>
                            <a href="https://mathsoftware.engineer">Engineer</a>
                        </li>
                    </ul>

                    <div>
                        Founder Edition Engineering Works
                    </div>
                </section>

                <section className="col-6">
                    <h5>
                        Social
                    </h5>

                    <div className="social my-2">
                        <div>Texsydo Open Source:</div>

                        <a
                            className="btn btn-github"
                            href="https://github.com/texsydo"
                            target="_blank"
                            rel="noreferrer"
                        >
                            <FontAwesomeIcon icon={ faGithub }></FontAwesomeIcon>
                        </a>

                        <br/>
                        <br/>
                        <div>Follow <b>MathSwe</b> to stay updated:</div>

                        <a
                            className="btn btn-linkedin"
                            href="https://www.linkedin.com/company/mathswe"
                            target="_blank"
                            rel="noreferrer"
                        >
                            <FontAwesomeIcon icon={ faLinkedin }></FontAwesomeIcon>
                        </a>
                    </div>
                </section>

                <section className="col-12 legal">
                    <h5>
                        Legal
                    </h5>

                    <h6>MVP: Texsydo, Web</h6>

                    <p>
                        Copyright © 2024 Tobias Briones. All rights reserved.
                    </p>

                    <div className="notice">
                        <p>
                            <b>Web App</b>
                            &nbsp;
                            licensed under the&nbsp;
                            <a
                                href="https://github.com/texsydo/texsydo---mvp/blob/main/LICENSE"
                                target="_blank"
                                rel="noreferrer"
                            >
                                GNU Affero General Public License v3.0 or later License
                            </a>
                            .
                        </p>
                        <p>
                            Consult other projects in the platform for their
                            respective license, notices, and legal information.
                        </p>
                    </div>
                </section>

                <section className="col-12 legal">
                    <h5 className="text-center">About</h5>

                    <p className="text-center">
                        Texsydo: Mathematical Text and Art.
                    </p>

                    <p className="text-center">
                        { APP_VERSION_LABEL }.
                    </p>
                </section>
            </div>
        </footer>
    </>;
}

export default Footer;
