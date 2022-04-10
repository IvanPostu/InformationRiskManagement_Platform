import { BaseApiProvider, IGraphQLQuery } from './BaseApiProvider'

export class AuthUserProvider extends BaseApiProvider {
  public async authUser(email: string, password: string): Promise<string | null> {
    try {
      const schemaObject: any = {
        query: `
          query {
            authUser(email: q, password: w)
            {
              email,
              token
            }            
          }
        `,
      }

      const data = this._performCall(schemaObject)

      // console.log(data)

      return ''
    } catch (e) {
      return null
    }
  }
}
