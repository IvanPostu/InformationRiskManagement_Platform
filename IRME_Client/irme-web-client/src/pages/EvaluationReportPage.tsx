import React, { Component, PropsWithChildren, ReactElement } from 'react'
import { connect } from 'react-redux'
import { Navigate } from 'react-router-dom'
import { ErrorResult } from '../api/models/ErrorResult'
import { IEvaluationReport } from '../api/models/IEvaluationReport'
import { SAEvaluationProvider } from '../api/SAEvaluationProvider'
import { EvaluationReport } from '../components/EvaluationReport'
import { FullScreenLoader } from '../components/FullScreenLoader'
import { MainLayout } from '../layouts/MainLayout'
import { RouterPropsType, withRouter } from '../router/withRouter'
import { GlobalStateType } from '../store/store'
import base64 from '../utils/base64'
import { toastMessage } from '../utils/toastMessage'

function mapStateToProps(state: GlobalStateType) {
  const { token } = state.auth

  return { authToken: token || '' }
}

type EvaluationReportPagePropsType = PropsWithChildren<RouterPropsType> & ReturnType<typeof mapStateToProps>
type EvaluationReportPageStateType = {
  redirectToHome: boolean
  report: IEvaluationReport | null
}

class EvaluationReportPageComponent extends Component<EvaluationReportPagePropsType, EvaluationReportPageStateType> {
  private readonly _processId: number
  private _isMounted: boolean

  private readonly _categoryId: number
  private readonly _organisationId: number
  private readonly _categoryName: string
  private readonly _organisationName: string

  private readonly _evaluationProvider: SAEvaluationProvider

  constructor(props: EvaluationReportPagePropsType) {
    super(props)
    const paramResolver = new URLSearchParams(this.props.location.search)

    this._isMounted = false
    this._processId = Number(paramResolver.get('processId')) || -1
    this._categoryId = Number(paramResolver.get('categoryId'))
    this._organisationId = Number(paramResolver.get('organisationId'))
    this._evaluationProvider = new SAEvaluationProvider()
    this._categoryName = base64.decode(paramResolver.get('categoryName') || '')
    this._organisationName = base64.decode(paramResolver.get('organisationName') || '')

    this.state = {
      report: null,
      redirectToHome: false,
    }
  }

  componentDidMount() {
    this._isMounted = true

    if (this._processId <= 0) {
      toastMessage({
        message: 'A avut loc eroare în procesul de afișare a rezultatului evaluării...',
        type: 'error',
      })
      this.setState({
        redirectToHome: true,
      })
    } else {
      const provider = new SAEvaluationProvider()
      provider.getEvaluationReport(this.props.authToken, this._processId).then((data) => {
        if (!this._isMounted) return

        if (data === null || data instanceof ErrorResult) {
          toastMessage({
            message: 'Nu e posibil de afișat raportul evaluării, încercați peste o perioadă de timp...',
            type: 'error',
          })

          this.setState({ redirectToHome: true })
        } else {
          this.setState({
            report: data,
          })
        }
      })
    }
  }

  componentWillUnmount() {
    this._isMounted = false
  }

  render(): ReactElement {
    const { redirectToHome, report } = this.state
    if (redirectToHome) return <Navigate to={'/'} />

    if (report === null) return <FullScreenLoader />

    return (
      <MainLayout>
        <EvaluationReport
          _processId={this._processId}
          report={report}
          _categoryId={this._categoryId}
          _categoryName={this._categoryName}
          _organisationId={this._organisationId}
          _organisationName={this._organisationName}
        />
      </MainLayout>
    )
  }
}

const EvaluationReportPage = connect(mapStateToProps)(withRouter(EvaluationReportPageComponent))

export default EvaluationReportPage
