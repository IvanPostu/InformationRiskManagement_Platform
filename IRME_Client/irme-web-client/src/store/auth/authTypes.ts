import { Action } from 'redux'

export enum authActionTypeConstants {
  CHANGE_AUTH_DATA = '@counter/CHANGE_AUTH_DATA',
  AUTHENTICATE_USER = '@counter/AUTHENTICATE_USER',
  DEAUTHENTICATE_USER = '@counter/DEAUTHENTICATE_USER',
}

export type AuthStateType = {
  readonly token: string | null
  readonly email: string
  readonly firstName: string
  readonly lastName: string
  readonly isAuthenticated: boolean
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

export type AuthRootActionType = ChangeAuthDataActionType | DeauthenticateActionType | AuthenticateActionType
