import React, { Component, ReactElement } from 'react'
import { Navigate } from 'react-router-dom'
import { FullScreenLoader } from '../components/FullScreenLoader'
import { MainLayout } from '../layouts/MainLayout'
import { withRouter, RouterPropsType } from '../router/withRouter'

class EvaluationPageComponent extends Component<RouterPropsType> {
  private _paramsExtractedWithSuccess = false
  private readonly _categoryId: number
  private readonly _organisationId: number

  constructor(props: RouterPropsType) {
    super(props)

    const paramResolver = new URLSearchParams(this.props.location.search)
    this._categoryId = Number(paramResolver.get('_categoryId'))
    this._organisationId = Number(paramResolver.get('_organisationId'))

    if (this._categoryId && this._organisationId) {
      this._paramsExtractedWithSuccess = true
    }
  }

  render(): ReactElement {
    if (!this._paramsExtractedWithSuccess) {
      return <Navigate to={'/?error=1'} />
    }

    return (
      <MainLayout>
        <FullScreenLoader />
        <div className="container">
          <div className="card-panel grey-text text-darken-4"></div>
        </div>
      </MainLayout>
    )
  }
}

export const EvaluationPage = withRouter(EvaluationPageComponent)
