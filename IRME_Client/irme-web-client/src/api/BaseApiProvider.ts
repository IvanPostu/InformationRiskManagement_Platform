import { IProviderConfiguration } from './IProviderConfiguration'

export interface IGraphQLQuery {
  operationName: string
  query: string
  variables: Record<string, string>
}

export class BaseApiProvider {
  public static config: IProviderConfiguration

  protected async _performCall<_RType>(graphQLSchema: IGraphQLQuery): Promise<_RType> {
    const fetchResult = await fetch('/graphql', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        Authorization: 'null',
        'X-REQUEST-TYPE': 'GraphQL',
      },
      body: JSON.stringify(graphQLSchema),
    })

    // console.log(fetchResult)
    const data = await fetchResult.json()
    console.log(data)

    return {} as _RType
  }
}
