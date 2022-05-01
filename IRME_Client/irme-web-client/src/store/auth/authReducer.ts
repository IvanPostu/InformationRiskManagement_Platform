import { Reducer } from 'redux'
import { putAuthDataInLocalStorage, cleanAuthDataInLocalStorage } from '../../utils/authStorage'
import { authActionTypeConstants as T, AuthRootActionType, AuthStateType } from './authTypes'

const initialState: AuthStateType = {
  token: '',
  email: '',
  firstName: '',
  lastName: '',
  isAuthenticated: false,
  isAuthRequestRunning: true,
}

export const authReducer: Reducer<AuthStateType, AuthRootActionType> = (
  state: AuthStateType = initialState,
  action: AuthRootActionType
) => {
  switch (action.type) {
    case T.CHANGE_AUTH_DATA:
      putAuthDataInLocalStorage({
        email: action.payload.email,
        firstName: action.payload.firstName,
        lastName: action.payload.lastName,
        token: action.payload.token || '',
      })

      return {
        ...state,
        ...action.payload,
        isAuthRequestRunning: false,
        isAuthenticated: action.payload.token !== null,
      }
    case T.DEAUTHENTICATE_USER:
      cleanAuthDataInLocalStorage()

      return {
        ...state,
        ...initialState,
        isAuthRequestRunning: false,
        isAuthenticated: false,
      }
    case T.AUTHENTICATE_USER:
    case T.EXTEND_AUTH_TOKEN:
      return {
        ...state,
        isAuthRequestRunning: true,
      }
    default:
      return state
  }
}
