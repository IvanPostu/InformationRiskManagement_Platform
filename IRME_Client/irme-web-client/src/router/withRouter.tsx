/* eslint-disable react/display-name */
import React, { PropsWithChildren } from 'react'
import { Location, NavigateFunction, useLocation, useNavigate } from 'react-router-dom'

export type RouterPropsType = {
  location: Location
  navigate: NavigateFunction
}

// eslint-disable-next-line @typescript-eslint/no-explicit-any
export function withRouter(Child: any) {
  return (props: PropsWithChildren<unknown>) => {
    const location = useLocation()
    const navigate = useNavigate()
    return <Child {...props} navigate={navigate} location={location} />
  }
}
