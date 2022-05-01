import { ThunkDispatch } from 'redux-thunk'
import { AuthUserProvider } from '../../api/AuthUserProvider'
import { ErrorResult } from '../../api/models/ErrorResult'
import { GlobalStateType } from '../store'
import {
  authActionTypeConstants as T,
  AuthenticateActionType,
  ExtendTokenActionType,
  ChangeAuthDataActionType,
  DeauthenticateActionType,
} from './authTypes'

type AuthenticateDispatchType = ThunkDispatch<
  GlobalStateType,
  void,
  AuthenticateActionType | DeauthenticateActionType | ChangeAuthDataActionType | ExtendTokenActionType
>
export function authenticate(email: string, password: string) {
  const authProvider = new AuthUserProvider()
  return async (dispatch: AuthenticateDispatchType) => {
    dispatch({ type: T.AUTHENTICATE_USER } as AuthenticateActionType)
    const data = await authProvider.authUser(email, password)

    if (data instanceof ErrorResult || data === null) {
      dispatch({ type: T.DEAUTHENTICATE_USER } as DeauthenticateActionType)
      alert('Authentication error, something went wrong!!!')
    } else {
      const { email, firstName, lastName, token } = data
      dispatch({
        type: T.CHANGE_AUTH_DATA,
        payload: {
          email,
          token,
          firstName,
          lastName,
        },
      } as ChangeAuthDataActionType)
    }
  }
}

export function extendAuthentication(email: string, firstName: string, lastName: string, token: string) {
  const authProvider = new AuthUserProvider()
  return async (dispatch: AuthenticateDispatchType) => {
    dispatch({ type: T.EXTEND_AUTH_TOKEN } as ExtendTokenActionType)
    const data = await authProvider.extendAuthToken(token)

    if (data instanceof ErrorResult || data === null) {
      dispatch({ type: T.DEAUTHENTICATE_USER } as DeauthenticateActionType)
      alert('Authentication error, something went wrong!!!')
    } else {
      dispatch({
        type: T.CHANGE_AUTH_DATA,
        payload: {
          email,
          token: data,
          firstName,
          lastName,
        },
      } as ChangeAuthDataActionType)
    }
  }
}
