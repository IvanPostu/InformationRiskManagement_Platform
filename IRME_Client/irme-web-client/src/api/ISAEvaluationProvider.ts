import { ErrorResult } from './models/ErrorResult'
import { IAnsweredQuestion } from './models/IAnsweredQuestion'
import { IEvaluationProcess } from './models/IEvaluationProcess'
import { IEvaluationReport } from './models/IEvaluationReport'
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

  finalizeEvaluation(
    authToken: string,
    organisationId: number,
    processId: number,
    forced?: boolean
  ): Promise<boolean | null | ErrorResult>

  getEvaluationReport(authToken: string, processId: number): Promise<IEvaluationReport | null | ErrorResult>

  getEvaluationProcesses(
    authToken: string,
    organisationId: number
  ): Promise<Array<IEvaluationProcess> | null | ErrorResult>
}
