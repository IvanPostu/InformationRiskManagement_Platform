import React, { Component } from 'react'
import { Navigate } from 'react-router-dom'
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

  render() {
    if (!this._paramsExtractedWithSuccess) {
      return <Navigate to={'/?error=1'} />
    }

    return <MainLayout>EvaluationPage</MainLayout>
  }
}

export const EvaluationPage = withRouter(EvaluationPageComponent)
