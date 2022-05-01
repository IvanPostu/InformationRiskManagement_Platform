import { BaseApiProvider } from './BaseApiProvider'
import { ErrorResult } from './models/ErrorResult'

export interface ISACategory {
  name: string
  description: string
  imageUrl: string
  categroyId: number
}

export class SACategoryProvider extends BaseApiProvider {
  private _categoriesQueryBuilder(action: string): string {
    return `
      {
        ${action} {
          categroyId,
          name,
          description,
          imageUrl
        }
      }
    `
  }

  public async getSecurityAssessmentCategories(authToken: string): Promise<Array<ISACategory> | null | ErrorResult> {
    const action = 'getSecurityAssessmentCategories'
    const query = this._categoriesQueryBuilder(action)
    const data = await this._performCall<Array<ISACategory>>({
      action,
      requestDocument: query,
      authToken,
    })

    return data
  }
}
