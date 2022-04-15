import { BaseApiProvider } from './BaseApiProvider'
import { ErrorResult } from './models/ErrorResult'

export interface IOrganisation {
  id: number
  name: string
  description: string
  created: string
  base64ImageLogo: string
}

export class OrganisationProvider extends BaseApiProvider {
  private _userOrganisationsQueryBuilder(action: string): string {
    return `
      {
        ${action} {
          id,
          name,
          description,
          created,
          base64ImageLogo,
        }
      }
    `
  }

  public async userOrganisations(authToken: string): Promise<Array<IOrganisation> | null | ErrorResult> {
    const action = 'userOrganisations'
    const query = this._userOrganisationsQueryBuilder(action)
    const data = await this._performCall<Array<IOrganisation>>(action, query, authToken)

    return data
  }
}
