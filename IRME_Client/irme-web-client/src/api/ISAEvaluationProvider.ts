import { ErrorResult } from './models/ErrorResult'
import { IAnsweredQuestion } from './models/IAnsweredQuestion'
import type { IQuestionData } from './models/IQuestionData'

export interface ISAEvaluationProvider {
  createEvaluationProcess(
    authToken: string,
    organisationId: number,
    categoryId: number
  ): Promise<number | null | ErrorResult>

  putAnswerToQuestion(
    authToken: string,
    questionId: number,
    answerId: number,
    processId: number
  ): Promise<boolean | null | ErrorResult>

  removeAnswerFromQuestion(
    authToken: string,
    questionId: number,
    answerId: number,
    processId: number
  ): Promise<boolean | null | ErrorResult>

  getSecurityAssessmentQuestionsDataByCategory(
    authToken: string,
    categoryId: number
  ): Promise<Array<IQuestionData> | null | ErrorResult>

  getProcessAnsweredQuestions(
    authToken: string,
    processId: number
  ): Promise<Array<IAnsweredQuestion> | null | ErrorResult>
}