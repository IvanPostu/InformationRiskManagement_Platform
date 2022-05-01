import React, { Component, ReactElement } from 'react'
import { connect } from 'react-redux'
import { Navigate } from 'react-router-dom'
import { SAEvaluationProvider } from '../api/SAEvaluationProvider'
import { FullScreenLoader } from '../components/FullScreenLoader'
import { MainLayout } from '../layouts/MainLayout'
import { withRouter, RouterPropsType } from '../router/withRouter'
import { GlobalStateType } from '../store/store'

function mapStateToProps(state: GlobalStateType) {
  const { token } = state.auth
  return {
    token,
  }
}

type EvaluationPageComponentPropsType = RouterPropsType & ReturnType<typeof mapStateToProps>

interface IEvaluationPageComponentState {
  isLoading: boolean
}

class EvaluationPageComponent extends Component<EvaluationPageComponentPropsType, IEvaluationPageComponentState> {
  private _paramsExtractedWithSuccess = false
  private readonly _categoryId: number
  private readonly _organisationId: number

  private readonly _evaluationProvider: SAEvaluationProvider

  private _isMounted = false

  constructor(props: EvaluationPageComponentPropsType) {
    super(props)
    const paramResolver = new URLSearchParams(this.props.location.search)

    this._categoryId = Number(paramResolver.get('_categoryId'))
    this._organisationId = Number(paramResolver.get('_organisationId'))
    this._evaluationProvider = new SAEvaluationProvider()

    this.state = {
      isLoading: true,
    }

    if (this._categoryId && this._organisationId) {
      this._paramsExtractedWithSuccess = true
      this.createEvaluation()
    }
  }

  private createEvaluation = async (): Promise<void> => {
    if (this.props.token !== null) {
      const data = await this._evaluationProvider.createEvaluationProcess(
        this.props.token,
        this._organisationId,
        this._categoryId
      )

      if (!this._isMounted) {
        return
      }

      console.log(data)
    }
  }

  componentDidMount() {
    this._isMounted = true
  }

  componentWillUnmount() {
    this._isMounted = false
  }

  render(): ReactElement {
    const { isLoading } = this.state
    if (!this._paramsExtractedWithSuccess) {
      return <Navigate to={'/?error=1'} />
    }

    return (
      <MainLayout>
        {isLoading && <FullScreenLoader />}
        <div className="container">
          <div className="card-panel grey-text text-darken-4"></div>
        </div>
      </MainLayout>
    )
  }
}

export const EvaluationPage = withRouter(connect(mapStateToProps, null)(EvaluationPageComponent))
