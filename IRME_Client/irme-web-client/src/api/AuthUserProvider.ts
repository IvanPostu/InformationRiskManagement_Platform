import { gql } from 'graphql-request'
import { BaseApiProvider } from './BaseApiProvider'
import { ErrorResult } from './models/ErrorResult'

interface IAuthUserResponse {
  token: string
  email: string
  firstName: string
  lastName: string
}

export class AuthUserProvider extends BaseApiProvider {
  public async authUser(email: string, password: string): Promise<IAuthUserResponse | null | ErrorResult> {
    const action = 'authUser'
    const query = gql`
      query {
        ${action}(email: "${email}", password: "${password}") {
          email
          token
          firstName
          lastName
        }
      }
    `
    const data = await this._performCall<IAuthUserResponse>({
      action,
      requestDocument: query,
    })

    return data
  }

  public async extendAuthToken(token: string): Promise<string | null | ErrorResult> {
    const action = 'extendToken'
    const query = gql`
      query {
        ${action}(oldToken: "${token}")
      }
    `
    const data = await this._performCall<string>({
      action,
      requestDocument: query,
    })

    return data
  }
}
