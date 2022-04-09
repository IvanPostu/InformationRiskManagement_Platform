import React from 'react'
import styled from 'styled-components'

const FooterContainer = styled.footer`
    background-color: #2c3e50;
`

const FooterCopyright = styled.footer`
    background-color: #060a0e5d !important;
`

export function Footer() {
    return (
        <FooterContainer className="page-footer">
            <div className="container">
                <div className="row">
                    <div className="col l6 s12">
                        <h5 className="white-text">Information Risk Management Expert</h5>
                        <p className="grey-text text-lighten-4"></p>
                    </div>
                    <div className="col l4 offset-l2 s12">
                        <h5 className="white-text">Informații</h5>
                        <ul>
                            <li>
                                <a className="grey-text text-lighten-3" href="#!">
                                    Proiect
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <FooterCopyright className="footer-copyright">
                <div className="container">
                    © 2022 Copyright Text
                    <a className="grey-text text-lighten-4 right" href="#!">
                        _
                    </a>
                </div>
            </FooterCopyright>
        </FooterContainer>
    )
}
