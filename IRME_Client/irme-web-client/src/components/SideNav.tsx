import React, { Component, Fragment } from 'react'
import { connect } from 'react-redux'
import { Link } from 'react-router-dom'
import { bindActionCreators } from 'redux'
import styled from 'styled-components'
import { authActionTypeConstants, DeauthenticateActionType } from '../store/auth/authTypes'
import { AppDispatch, GlobalStateType } from '../store/store'
const M = require('materialize-css/dist/js/materialize.min.js')

const StyledNav = styled.nav`
  background: #2c3e50;
`

function mapStateToProps(state: GlobalStateType) {
  const { isAuthenticated, email, firstName, lastName } = state.auth
  return { isAuthenticated, email, firstName, lastName }
}

function mapDispatchToProps(dispatch: AppDispatch) {
  const logout = () => ({ type: authActionTypeConstants.DEAUTHENTICATE_USER } as DeauthenticateActionType)

  return bindActionCreators({ logout }, dispatch)
}

type SideNavPropsType = ReturnType<typeof mapStateToProps> & ReturnType<typeof mapDispatchToProps>

class SideNavComponent extends Component<SideNavPropsType> {
  private readonly _adminUrl = 'http://localhost:8081'

  componentDidMount() {
    const elems = document.querySelectorAll('.sidenav')
    M.Sidenav.init(elems, {})

    const dropdowns = document.querySelector('#profileDropDownBtnId')
    M.Dropdown.init(dropdowns, {
      inDuration: 300,
      outDuration: 225,
      constrain_width: false, // Does not change width of dropdown to that of the activator
      hover: true, // Activate on hover
      gutter: 0, // Spacing from edge
      belowOrigin: false, // Displays dropdown below the button
      alignment: 'left', // Displays dropdown with edge aligned to the left of button
    })
  }

  hideSideMenu = () => {
    const node = document.querySelector('#sidemenuId')
    if (node) {
      const instance = M.Sidenav.getInstance(node)
      instance.close()
    }
  }

  render() {
    const { isAuthenticated, firstName, lastName, email } = this.props
    const desktopElements = isAuthenticated ? (
      <Fragment>
        <li>
          <Link to={'/categories'}>Categorii de evaluari</Link>
        </li>
        <li>
          <a href={this._adminUrl}>Control administrator</a>
        </li>
        <li>
          <a className="dropdown-trigger" data-target="profileDropDownId" id="profileDropDownBtnId">
            Profil Utilizator<i className="material-icons right">arrow_drop_down</i>
          </a>
          <ul id="profileDropDownId" className="dropdown-content">
            <li>
              <a className="" href="#">
                Informații
              </a>
            </li>
            <li>
              <a className="" href="#">
                Platforma
              </a>
            </li>
            <li className="divider"></li>
            <li>
              <a onClick={this.props.logout} className="red-text" href="#">
                Deautentificare
              </a>
            </li>
          </ul>
        </li>
      </Fragment>
    ) : (
      <li>
        <Link to={'/login'}>Autentificare</Link>
      </li>
    )

    const mobileMenu = isAuthenticated ? (
      <Fragment>
        <li>
          <div className="user-view">
            <a href="#user">
              <img style={{ width: '60px', height: '60px' }} src="defaultUserImage.png" />
            </a>
            <a href="#name">
              <span className="name  grey-text text-darken-3">{firstName + ' ' + lastName}</span>
            </a>
            <a href="#email">
              <span className="grey-text text-darken-1 email">{email}</span>
            </a>
            <a
              onClick={() => {
                this.hideSideMenu()
                this.props.logout()
              }}
              className="red-text"
              href="#"
            >
              Deautentificare
            </a>
          </div>
        </li>
        <li>
          <a className="" href="#">
            Informații
          </a>
        </li>
        <li>
          <div className="divider"></div>
        </li>
        <li>
          <Link onClick={this.hideSideMenu} className="" to={'/categories'}>
            Categorii de evaluari
          </Link>
        </li>
        <li>
          <a href={this._adminUrl} className="">
            Control administrator
          </a>
        </li>
      </Fragment>
    ) : (
      <Fragment>
        <li>
          <div className="user-view">
            <a href="#user">
              <img style={{ width: '60px', height: '60px' }} src="defaultUserImage.png" />
            </a>
            <a href="#name">
              <span className="name  grey-text text-darken-3">Guest</span>
            </a>
            <a href="#email">
              <span className="grey-text text-darken-1 email"></span>
            </a>
          </div>
        </li>
        <li>
          <Link to={'/login'} onClick={this.hideSideMenu}>
            <i className="material-icons">person</i>
            <span>Autentificare</span>
          </Link>
        </li>
        <li>
          <a className="" href="#">
            Informații
          </a>
        </li>
        <li>
          <div className="divider"></div>
        </li>
        <li>
          <Link className="subheader" to={'/categories'}>
            Categorii de evaluari
          </Link>
        </li>
        <li>
          <a href={this._adminUrl} className="subheader">
            Control administrator
          </a>
        </li>
      </Fragment>
    )

    return (
      <Fragment>
        <div className="navbar-fixed">
          <StyledNav>
            <div className="nav-wrapper">
              <a href="#" data-target="sidemenuId" className="sidenav-trigger">
                <i className="material-icons">menu</i>
              </a>
              <a href="#" className="brand-logo">
                <div className="background">
                  <img style={{ width: '150px' }} src="logo-removebg-preview.png" />
                </div>
              </a>
              <ul className="side-nav right hide-on-med-and-down" id="mobile-demo">
                {desktopElements}
              </ul>
            </div>
          </StyledNav>
        </div>

        <ul id="sidemenuId" className="sidenav indigo lighten-5">
          {mobileMenu}
        </ul>
      </Fragment>
    )
  }
}

// eslint-disable-next-line @typescript-eslint/no-explicit-any
export const SideNav = connect(mapStateToProps, mapDispatchToProps)(SideNavComponent as any)
