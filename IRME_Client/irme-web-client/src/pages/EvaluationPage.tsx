import React, { Component, Fragment, ReactElement } from 'react'
import { connect } from 'react-redux'
import { ErrorResult } from '../api/models/ErrorResult'
import { IQuestionData } from '../api/models/IQuestionData'
import { SAEvaluationProvider } from '../api/SAEvaluationProvider'
import { FullScreenLoader } from '../components/FullScreenLoader'
import { SAEvaluation } from '../components/SAEvaluation'
import { RouterPropsType, withRouter } from '../router/withRouter'
import { GlobalStateType } from '../store/store'
import base64 from '../utils/base64'

function mapStateToProps(state: GlobalStateType) {
  const { token } = state.auth
  return {
    token,
  }
}

type EvaluationPageComponentPropsType = RouterPropsType & ReturnType<typeof mapStateToProps>

interface IEvaluationPageComponentState {
  questions: Array<IQuestionData>
  evaluationProcessId: number
}

class EvaluationPageComponent extends Component<EvaluationPageComponentPropsType, IEvaluationPageComponentState> {
  private _paramsExtractedWithSuccess = false
  private readonly _categoryId: number
  private readonly _organisationId: number
  private readonly _categoryName: string
  private readonly _organisationName: string

  private readonly _evaluationProvider: SAEvaluationProvider

  private _isMounted = false

  constructor(props: EvaluationPageComponentPropsType) {
    super(props)
    const paramResolver = new URLSearchParams(this.props.location.search)

    this._categoryId = Number(paramResolver.get('categoryId'))
    this._organisationId = Number(paramResolver.get('organisationId'))
    this._evaluationProvider = new SAEvaluationProvider()
    this._categoryName = base64.decode(paramResolver.get('categoryName') || '')
    this._organisationName = base64.decode(paramResolver.get('organisationName') || '')

    this.state = {
      evaluationProcessId: Number(paramResolver.get('processId')) || -1,
      questions: new Array<IQuestionData>(),
    }

    if (this._categoryId && this._organisationId) {
      this._paramsExtractedWithSuccess = true
      this.fetchQuestionsData()
    }
  }

  private fetchQuestionsData = async (): Promise<void> => {
    const token = this.props.token || ''

    const questionsData = await this._evaluationProvider.getSecurityAssessmentQuestionsDataByCategory(
      token,
      this._categoryId
    )
    if (!this._isMounted) return

    if (questionsData !== null && !(questionsData instanceof ErrorResult)) {
      this.setState({
        questions: questionsData,
      })
    } else {
      console.error(`Can't fetch questions: ${questionsData}`)
      this.props.navigate('/error/1')
    }
  }

  componentDidMount() {
    this._isMounted = true

    if (!this._paramsExtractedWithSuccess) {
      this.props.navigate('/error/1')
    }
  }

  componentWillUnmount() {
    this._isMounted = false
  }

  render(): ReactElement {
    const { evaluationProcessId, questions } = this.state
    const isLoading = evaluationProcessId === -1 || questions.length === 0

    if (isLoading) return <FullScreenLoader />

    return (
      <Fragment>
        <SAEvaluation
          token={this.props.token || ''}
          evaluationProcessId={evaluationProcessId}
          questions={questions}
          categoryName={this._categoryName}
          organisationName={this._organisationName}
          organisationId={this._organisationId}
        />
      </Fragment>
    )
  }
}

export const EvaluationPage = withRouter(connect(mapStateToProps, null)(EvaluationPageComponent))
