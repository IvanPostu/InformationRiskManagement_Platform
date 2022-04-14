import { BaseApiProvider } from './BaseApiProvider'
import { ErrorResult } from './models/ErrorResult'

interface IAuthUserResponse {
  token: string
  email: string
  firstName: string
  lastName: string
}

export class AuthUserProvider extends BaseApiProvider {
  private _authQueryBuilder(action: string, email: string, password: string): string {
    return `
      {
        ${action}(email: "${email}", password: "${password}") {
          email
          token
          firstName
          lastName
        }
      }
    `
  }

  public async authUser(email: string, password: string): Promise<IAuthUserResponse | null | ErrorResult> {
    const action = 'authUser'
    const query = this._authQueryBuilder(action, email, password)
    const data = await this._performCall<IAuthUserResponse>(action, query)

    return data
  }
}
