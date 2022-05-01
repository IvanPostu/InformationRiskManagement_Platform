import { GraphQLClient } from 'graphql-request'
import { IProviderConfiguration } from './IProviderConfiguration'
import { ErrorResult } from './models/ErrorResult'
import { IError } from './models/IError'

interface IPerformCallProps {
  action: string
  requestDocument: string
  authToken?: string
  headers?: Record<string, string>
}

export class BaseApiProvider {
  public static config: IProviderConfiguration

  // eslint-disable-next-line @typescript-eslint/no-explicit-any
  private tryToExtractArrayWithErrors = (e: any): Array<IError> | null => {
    let errors: Array<IError> | null = null

    if (e && Object.hasOwn(e, 'response') && Object.hasOwn(e['response'], 'errors')) {
      errors = e.response.errors as Array<IError>
    }

    return errors
  }

  protected async _performCall<_RType>(props: IPerformCallProps): Promise<_RType | ErrorResult | null> {
    const { action, requestDocument, authToken, headers } = props

    try {
      const client = new GraphQLClient('/graphql')
      const defaultHeaders = headers ? headers : {}
      defaultHeaders['Authorization'] = authToken ? `Bearer_${authToken}` : 'null'

      client.setHeaders(defaultHeaders)

      const result = await client.request(requestDocument)

      return result[action] as _RType
    } catch (e) {
      const mappedErrors = this.tryToExtractArrayWithErrors(e)
      if (mappedErrors !== null) {
        const result = new ErrorResult(mappedErrors)
        const errorHandlers = BaseApiProvider.config.errorCodeHandler

        if (errorHandlers) {
          Object.keys(errorHandlers).forEach((k) => {
            const errorCode = Number(k)
            if (result.containsErrorCode(errorCode)) {
              new Promise((resolve) => {
                errorHandlers[errorCode]()
                resolve(null)
              })
            }
          })
        }

        return result
      }
      console.error(e)
      return null
    }
  }
}
