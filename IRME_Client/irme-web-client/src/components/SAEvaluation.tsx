import React, { Component, ReactElement } from 'react'
import { CardPanel } from 'react-materialize'
import styled from 'styled-components'
import { ISAEvaluationProvider } from '../api/ISAEvaluationProvider'
import { ErrorResult } from '../api/models/ErrorResult'
import { IQuestionData } from '../api/models/IQuestionData'
import { SAEvaluationProvider } from '../api/SAEvaluationProvider'
import { MainLayout } from '../layouts/MainLayout'
import { FullScreenLoader } from './FullScreenLoader'
import { SAEvaluationItem } from './SAEvaluationItem'

interface SAEvaluationPropsType {
  questions: Array<IQuestionData>
  evaluationProcessId: number
  categoryName: string
  organisationName: string
  token: string
}

interface SAEvaluationStateType {
  questionAnswerIds: Set<number>
  isFetching: boolean
}

const EvaluationHeaderContainer = styled.div`
  margin: 10px 0 30px 0;
`

export class SAEvaluation extends Component<SAEvaluationPropsType, SAEvaluationStateType> {
  private readonly _evaluationProvider: ISAEvaluationProvider
  private _isMounted = false

  constructor(props: SAEvaluationPropsType) {
    super(props)

    this.state = {
      questionAnswerIds: new Set(),
      isFetching: true,
    }

    this._evaluationProvider = new SAEvaluationProvider()
  }

  private fetchQuestionsAndAnswersState = async (): Promise<void> => {
    const questionAnswers = await this._evaluationProvider.getProcessAnsweredQuestions(
      this.props.token,
      this.props.evaluationProcessId
    )
    if (Array.isArray(questionAnswers)) {
      const newSet = new Set<number>()
      questionAnswers.forEach((q) => newSet.add(q.questionAnswerId))
      this.setState({
        questionAnswerIds: newSet,
        isFetching: false,
      })
    }
  }

  private onAnswerSelect = (questionId: number, answerId: number, questionAnswerId: number): void => {
    if (this.state.isFetching) return

    this.setState({
      isFetching: true,
    })
    this.onAnswerSelectAsync(questionId, answerId, questionAnswerId)
  }

  private onAnswerSelectAsync = async (
    questionId: number,
    answerId: number,
    questionAnswerId: number
  ): Promise<void> => {
    let data: boolean | ErrorResult | null
    let removed: boolean

    if (this.state.questionAnswerIds.has(questionAnswerId)) {
      removed = true
      data = await this._evaluationProvider.removeAnswerFromQuestion(
        this.props.token,
        questionId,
        answerId,
        this.props.evaluationProcessId
      )
    } else {
      removed = false
      data = await this._evaluationProvider.putAnswerToQuestion(
        this.props.token,
        questionId,
        answerId,
        this.props.evaluationProcessId
      )
    }

    if (!this._isMounted) return

    if (data !== null && !(data instanceof ErrorResult)) {
      if (data) {
        const newSet = new Set<number>(this.state.questionAnswerIds)
        if (removed) {
          newSet.delete(questionAnswerId)
        } else {
          newSet.add(questionAnswerId)
        }

        this.setState({
          questionAnswerIds: newSet,
        })
      }
    }

    this.setState({
      isFetching: false,
    })
  }

  componentDidMount() {
    this._isMounted = true
    this.fetchQuestionsAndAnswersState()
  }

  componentWillUnmount() {
    this._isMounted = false
  }

  render(): ReactElement {
    const { evaluationProcessId, questions, categoryName, organisationName } = this.props
    const { questionAnswerIds, isFetching } = this.state
    const isLoading = evaluationProcessId === -1 || questions.length === 0 || isFetching

    const questionsItems = questions.map((questionData) => {
      return (
        <SAEvaluationItem
          onSelect={this.onAnswerSelect}
          key={questionData.questionId}
          {...questionData}
          questionAnswerIds={questionAnswerIds}
        />
      )
    })

    return (
      <MainLayout>
        {isLoading && <FullScreenLoader />}
        <div className="container">
          <CardPanel className="card-panel grey-text text-darken-4">
            <EvaluationHeaderContainer>
              <h4>Proces de evaluare a riscurilor #{evaluationProcessId}</h4>
              <div className="modal-footer grey-text text-darken-1">Organizație: {organisationName}</div>
              <div className="modal-footer grey-text text-darken-1">Categorie de evaluare: {categoryName}</div>
            </EvaluationHeaderContainer>

            <section>{questionsItems}</section>
          </CardPanel>
        </div>
      </MainLayout>
    )
  }
}