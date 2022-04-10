import { BaseApiProvider } from './BaseApiProvider'
import { request } from 'graphql-request'

interface IAuthUserResponse {
  authUser: {
    token: string
    email: string
  }
}

export class AuthUserProvider extends BaseApiProvider {
  private _authQueryBuilder(email: string, password: string): string {
    return `
      {
        authUser(email: "${email}", password: "${password}") {
          email
          token
        }
      }
    `
  }

  public async authUser(email: string, password: string): Promise<IAuthUserResponse | null> {
    try {
      const data: IAuthUserResponse = await request('/graphql', this._authQueryBuilder(email, password))
      return data
    } catch (e) {
      return null
    }
  }
}
