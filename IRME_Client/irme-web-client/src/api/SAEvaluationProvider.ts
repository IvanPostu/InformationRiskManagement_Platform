import { gql } from 'graphql-request'
import { BaseApiProvider } from './BaseApiProvider'
import { ISAEvaluationProvider } from './ISAEvaluationProvider'
import { ErrorResult } from './models/ErrorResult'
import { IAnsweredQuestion } from './models/IAnsweredQuestion'
import { IEvaluationProcess } from './models/IEvaluationProcess'
import { IEvaluationReport } from './models/IEvaluationReport'
import { IEvaluationResult } from './models/IEvaluationResult'
import { IQuestionData } from './models/IQuestionData'

export class SAEvaluationProvider extends BaseApiProvider implements ISAEvaluationProvider {
  public async createEvaluationProcess(
    authToken: string,
    organisationId: number,
    categoryId: number
  ): Promise<number | null | ErrorResult> {
    const action = 'createEvaluationProcess'
    const mutation = gql`
      mutation {
        ${action}(organisationId: ${organisationId}, categoryId: ${categoryId})
      }
    `

    const data = await this._performCall<number>({
      action,
      requestDocument: mutation,
      authToken,
    })

    return data
  }

  public async putAnswerToQuestion(
    authToken: string,
    questionId: number,
    answerId: number,
    processId: number
  ): Promise<boolean | null | ErrorResult> {
    const action = 'putAnswerToQuestion'
    const mutation = gql`
      mutation {
        ${action}(questionId: ${questionId}, answerId: ${answerId}, processId: ${processId})
      }
    `
    const data = await this._performCall<boolean>({
      action,
      requestDocument: mutation,
      authToken,
    })

    return data
  }

  public async removeAnswerFromQuestion(
    authToken: string,
    questionId: number,
    answerId: number,
    processId: number
  ): Promise<boolean | null | ErrorResult> {
    const action = 'removeAnswerFromQuestion'
    const mutation = gql`
      mutation {
        ${action}(questionId: ${questionId}, answerId: ${answerId}, processId: ${processId})
      }
    `
    const data = await this._performCall<boolean>({
      action,
      requestDocument: mutation,
      authToken,
    })

    return data
  }

  public async getSecurityAssessmentQuestionsDataByCategory(
    authToken: string,
    categoryId: number
  ): Promise<Array<IQuestionData> | null | ErrorResult> {
    const action = 'getSecurityAssessmentQuestionsDataByCategory'
    const query = gql`
      query {
          ${action}(categoryId: ${categoryId})
          {
              questionId,
              parentQuestionAnswerId,
              hasMultipleAnswers,
              questionWeight,
              answers{
                  answerId,
                  questionAnswerId,
                  answer,
              },
              question,
          }
      }
    `
    const data = await this._performCall<Array<IQuestionData>>({
      action,
      requestDocument: query,
      authToken,
    })

    return data
  }

  public async getProcessAnsweredQuestions(
    authToken: string,
    processId: number
  ): Promise<Array<IAnsweredQuestion> | null | ErrorResult> {
    const action = 'getProcessAnsweredQuestions'
    const query = gql`
      query {
        ${action}(processId: ${processId})
        {
            questionAnswerId,
            questionId,
            answerId,
        }
      }
    `
    const data = await this._performCall<Array<IAnsweredQuestion>>({
      action,
      requestDocument: query,
      authToken,
    })

    return data
  }

  public async finalizeEvaluation(
    authToken: string,
    organisationId: number,
    processId: number,
    forced = false
  ): Promise<boolean | null | ErrorResult> {
    const action = `finalizeEvaluation${forced ? 'Forced' : ''}`
    const query = gql`
      mutation {
          ${action}(organisationId: ${organisationId}, processId: ${processId})
      }
    `
    const data = await this._performCall<boolean>({
      action,
      requestDocument: query,
      authToken,
    })

    return data
  }

  public async getEvaluationReport(
    authToken: string,
    processId: number
  ): Promise<IEvaluationReport | null | ErrorResult> {
    const action = 'getEvaluationReport'
    const query = gql`
      query {
        ${action}( processId: ${processId} ){
          maxCategoryWeight,
          totalProcessWeight,
          expectedProcessWeight,
          items {
            questionId,
            answerId,
            question,
            answer,
            description,
          }
        }
      }
    `
    const data = await this._performCall<IEvaluationReport>({
      action,
      requestDocument: query,
      authToken,
    })

    return data
  }

  public async getEvaluationProcesses(
    authToken: string,
    organisationId: number
  ): Promise<Array<IEvaluationProcess> | null | ErrorResult> {
    const action = 'getEvaluationProcesses'
    const query = gql`
      query {
          getEvaluationProcesses(organisationId: ${organisationId})
          {
              processId,
              organisationId,
              organisationName,
              created,
              status,
              statusCode,
              userId,
              userEmail,
              categoryId,
              categoryName,
          }
      }
    `
    const data = await this._performCall<Array<IEvaluationProcess>>({
      action,
      requestDocument: query,
      authToken,
    })

    return data
  }

  public async getEvaluationsResults(
    authToken: string,
    organisationId: number,
    limitsPerCategory: number,
    categoryId: number | null
  ): Promise<Array<IEvaluationResult> | null | ErrorResult> {
    const action = 'getEvaluationsResults'
    const query = gql`
      query {
          ${action}(organisationId: ${organisationId}, categoryId: ${categoryId}, limitsPerCategory: ${limitsPerCategory})
          {
            processId,
            categoryId,
            categoryName,
            answerExpectedWeight,
            created,
            answerTotalWeight,
            answerMaxWeight,
            statusCode,
          }
      }
    `
    const data = await this._performCall<Array<IEvaluationResult>>({
      action,
      requestDocument: query,
      authToken,
    })

    return data
  }
}
