import React, { Fragment } from 'react'
import { Organisations } from '../components/Organisations'
import { MainLayout } from '../layouts/MainLayout'

export default function OrganisationsPage() {
  return (
    <Fragment>
      <MainLayout>
        <Organisations />
      </MainLayout>
    </Fragment>
  )
}
