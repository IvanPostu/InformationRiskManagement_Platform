import { gql } from 'graphql-request'
import { BaseApiProvider } from './BaseApiProvider'
import { ISAEvaluationProvider } from './ISAEvaluationProvider'
import { ErrorResult } from './models/ErrorResult'
import { IAnsweredQuestion } from './models/IAnsweredQuestion'
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
}
