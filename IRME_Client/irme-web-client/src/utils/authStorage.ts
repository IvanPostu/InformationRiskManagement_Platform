interface IAuthParams {
  firstName: string
  lastName: string
  email: string
  token: string
}

export function putAuthDataInLocalStorage(params: IAuthParams) {
  localStorage.setItem('AUTH_DATA', JSON.stringify(params))
}

export function cleanAuthDataInLocalStorage() {
  localStorage.removeItem('AUTH_DATA')
}

export function getAuthDataFromLocalStorage(): null | IAuthParams {
  const json = localStorage.getItem('AUTH_DATA')

  if (json !== null) {
    return JSON.parse(json)
  }

  return null
}
