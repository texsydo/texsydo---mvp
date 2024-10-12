// Copyright (c) 2024 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: AGPL-3.0-or-later
// This file is part of https://github.com/texsydo/texsydo---mvp

import "./Header.css";
import { Icon } from "@components/Article/Heading/Heading.tsx";
import { useEffect, useState } from "react";

type HeaderProps = {
    appName: string,
    icon: Icon,
}

function Header({ appName, icon }: HeaderProps) {
    const [ navHideMode, setNavHideMode ] = useState<string>("");
    const [ lastScrollTop, setLastScrollTop ] = useState<number>(0);

    useEffect(() => {
        const headingHeightPx = 52; // 52px desktop, 38px mobile
        const headingSectionBottom = getHeadingSectionBottom();

        const handleScroll = () => {
            const scrollTop = window.scrollY;

            // Scroll down
            if (scrollTop > lastScrollTop) {
                const closeScroll = headingSectionBottom > 0;

                setNavHideMode(closeScroll ? "compact" : "hidden");
            }
            // Scroll up
            else {
                const farScroll = scrollTop > headingHeightPx;

                setNavHideMode(farScroll ? "compact" : "");
            }
            setLastScrollTop(scrollTop <= 0 ? 0 : scrollTop);
        };

        window.addEventListener("scroll", handleScroll);
        return () => {
            window.removeEventListener("scroll", handleScroll);
        };
    }, [ lastScrollTop ]);

    return <>
        <header>
            <nav
                className={ `d-flex p-0 fixed-top ${ navHideMode }` }
            >
                <div className="bg"></div>
                <ul className="navbar-nav center">
                    <li className="nav-item d-flex flex-column">
                        <a
                            className="navbar-brand nav-link d-flex flex-fill p-0"
                            href="/"
                        >
                            <h1>{ appName }</h1>
                            <img
                                src={ icon.src }
                                alt={ icon.name }
                                className="nav-icon"
                            />
                        </a>
                    </li>
                </ul>
            </nav>
        </header>
    </>;
}

function getHeadingSectionBottom() {
    const element = document.getElementById("math");
    return element?.getBoundingClientRect().bottom ?? 0;
}

export default Header;
