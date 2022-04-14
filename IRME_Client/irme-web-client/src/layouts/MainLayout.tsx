import React, { Component, Fragment, PropsWithChildren } from 'react'
import { Footer } from '../components/Footer'
import { SideNav } from '../components/SideNav'

type MainLayoutPropsType = PropsWithChildren<unknown>

export class MainLayout extends Component<MainLayoutPropsType> {
  render(): JSX.Element {
    return (
      <Fragment>
        <SideNav />

        <main style={{ minHeight: '100vh' }}>{this.props.children}</main>

        <Footer />
      </Fragment>
    )
  }
}
