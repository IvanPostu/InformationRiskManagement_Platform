import React, { Component, Fragment } from 'react'
import { Organisations } from '../components/Organisations'
import { MainLayout } from '../layouts/MainLayout'

export class OrganisationsPage extends Component {
  render() {
    return (
      <Fragment>
        <MainLayout>
          <Organisations />
        </MainLayout>
      </Fragment>
    )
  }
}
