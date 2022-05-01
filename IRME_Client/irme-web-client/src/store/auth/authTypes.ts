import { Action } from 'redux'

export enum authActionTypeConstants {
  CHANGE_AUTH_DATA = '@auth/CHANGE_AUTH_DATA',
  AUTHENTICATE_USER = '@auth/AUTHENTICATE_USER',
  DEAUTHENTICATE_USER = '@auth/DEAUTHENTICATE_USER',
  EXTEND_AUTH_TOKEN = '@auth/EXTEND_AUTH_TOKEN',
}

export type AuthStateType = {
  readonly token: string | null
  readonly email: string
  readonly firstName: string
  readonly lastName: string
  readonly isAuthenticated: boolean
  readonly isAuthRequestRunning: boolean
}

export type ChangeAuthDataActionType = Action<authActionTypeConstants.CHANGE_AUTH_DATA> & {
  payload: {
    token: string | null
    email: string
    firstName: string
    lastName: string
  }
}

export type AuthenticateActionType = Action<authActionTypeConstants.AUTHENTICATE_USER>

export type DeauthenticateActionType = Action<authActionTypeConstants.DEAUTHENTICATE_USER>

export type ExtendTokenActionType = Action<authActionTypeConstants.EXTEND_AUTH_TOKEN>

export type AuthRootActionType =
  | ChangeAuthDataActionType
  | DeauthenticateActionType
  | AuthenticateActionType
  | ExtendTokenActionType
