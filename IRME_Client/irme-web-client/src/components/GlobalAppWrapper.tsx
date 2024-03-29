import React, { Fragment, PropsWithChildren, useEffect } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import { extendAuthentication } from '../store/auth/authActionCreators'
import { authActionTypeConstants as T, DeauthenticateActionType } from '../store/auth/authTypes'
import { AppDispatch, GlobalStateType } from '../store/store'
import { getAuthDataFromLocalStorage } from '../utils/authStorage'
import { FullScreenLoader } from './FullScreenLoader'

export const GlobalAppWrapper = (props: PropsWithChildren<unknown>) => {
  const dispatch = useDispatch<AppDispatch>()

  const { isAuthRequestRunning } = useSelector((state: GlobalStateType) => {
    const { isAuthRequestRunning } = state.auth
    return { isAuthRequestRunning }
  })

  useEffect(() => {
    const authData = getAuthDataFromLocalStorage()
    if (authData !== null) {
      const { email, firstName, lastName, token } = authData
      dispatch(extendAuthentication(email, firstName, lastName, token))
    } else {
      dispatch({ type: T.DEAUTHENTICATE_USER } as DeauthenticateActionType)
    }
  }, [])

  return <Fragment>{isAuthRequestRunning ? <FullScreenLoader /> : props.children}</Fragment>
}
