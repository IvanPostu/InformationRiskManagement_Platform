import { Reducer } from 'redux'
import { authActionTypeConstants as T, AuthRootActionType, AuthStateType } from './authTypes'

const initialState: AuthStateType = {
  token: '',
  email: '',
  firstName: '',
  lastName: '',
  isAuthenticated: false,
}

export const authReducer: Reducer<AuthStateType, AuthRootActionType> = (
  state: AuthStateType = initialState,
  action: AuthRootActionType
) => {
  switch (action.type) {
    case T.CHANGE_AUTH_DATA:
      return {
        ...state,
        ...action.payload,
        isAuthenticated: action.payload.token !== null,
      }
    case T.DEAUTHENTICATE_USER:
      return {
        ...state,
        ...initialState,
        isAuthenticated: false,
      }
    case T.AUTHENTICATE_USER:
    default:
      return state
  }
}
