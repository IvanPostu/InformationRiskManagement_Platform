import React, { Component, Fragment, PropsWithChildren, ReactElement } from 'react'
import { Footer } from '../components/Footer'
import { SideNav } from '../components/SideNav'

type MainLayoutPropsType = PropsWithChildren<unknown>

export class MainLayout extends Component<MainLayoutPropsType> {
  render() {
    return (
      <Fragment>
        <SideNav />
        <main style={{ minHeight: '100vh' }}>{this.props.children}</main>

        <Footer />
      </Fragment>
    )
  }
}
