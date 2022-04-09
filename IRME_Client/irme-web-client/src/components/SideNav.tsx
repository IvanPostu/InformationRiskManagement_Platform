import React, { Component, Fragment } from 'react'
import { Link } from 'react-router-dom'
const M = require('materialize-css/dist/js/materialize.min.js')
import styled from 'styled-components'

const StyledNav = styled.nav`
    background: #2c3e50;
`

export class SideNav extends Component {
    componentDidMount() {
        const elems = document.querySelectorAll('.sidenav')
        M.Sidenav.init(elems, {})
    }

    render() {
        return (
            <Fragment>
                <div className="navbar-fixed">
                    <StyledNav>
                        <div className="nav-wrapper">
                            <a href="#" data-target="slide-out" className="sidenav-trigger">
                                <i className="material-icons">menu</i>
                            </a>
                            <a href="#" className="brand-logo">
                                <div className="background">
                                    <img style={{ width: '150px' }} src="logo-removebg-preview.png" />
                                </div>
                            </a>
                            <ul className="side-nav right hide-on-med-and-down" id="mobile-demo">
                                <li>
                                    <Link to={'/categories'}>Categorii de evaluari</Link>
                                </li>
                                <li>
                                    <a id="searchMenu" href="#">
                                        Control administrator
                                    </a>
                                </li>
                                <li>
                                    <a id="profileMenu" href="#">
                                        Profil
                                    </a>
                                </li>
                                <li>
                                    <a id="loginMenu" href="#">
                                        Deautentificare
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </StyledNav>
                </div>

                <ul id="slide-out" className="sidenav indigo lighten-5">
                    <li>
                        <div className="user-view">
                            <a href="#user">
                                {/* <img className="circle" src="images/yuna.jpg" /> */}
                                <img style={{ width: '60px', height: '60px' }} src="defaultUserImage.png" />
                            </a>
                            <a href="#name">
                                <span className="white-text name  grey-text text-darken-3">John Doe</span>
                            </a>
                            <a href="#email">
                                <span className="white-text email">jdandturk@gmail.com</span>
                            </a>
                        </div>
                    </li>
                    <li>
                        <a href="#!">
                            <i className="material-icons">cloud</i>First Link With Icon
                        </a>
                    </li>
                    <li>
                        <a href="#!">Second Link</a>
                    </li>
                    <li>
                        <div className="divider"></div>
                    </li>
                    <li>
                        <a className="subheader">Subheader</a>
                    </li>
                    <li>
                        <a className="waves-effect" href="#!">
                            Third Link With Waves
                        </a>
                    </li>
                </ul>
            </Fragment>
        )
    }
}
