import { IProviderConfiguration } from './IProviderConfiguration'

export class BaseApiProvider {
  public static config: IProviderConfiguration
  private _endpointUrl: string

  public constructor(endpointUrl: string) {
    this._endpointUrl = endpointUrl
  }

  protected async _performCall<_RType>(graphQLSchema: string /*, variables?: any*/): Promise<_RType> {
    const fetchResult = await fetch(this._endpointUrl, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        query: graphQLSchema,
        // variables: {
        //   now: new Date().toISOString(),
        // },
      }),
    })

    console.log(fetchResult)
    const data = await fetchResult.json()
    console.log(data)

    return {} as _RType
  }
}
