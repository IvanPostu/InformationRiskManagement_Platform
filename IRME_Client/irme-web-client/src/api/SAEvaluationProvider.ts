import { gql } from 'graphql-request'
import { BaseApiProvider } from './BaseApiProvider'
import { ErrorResult } from './models/ErrorResult'

export class SAEvaluationProvider extends BaseApiProvider {
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
}
