/* eslint-disable @typescript-eslint/no-explicit-any */
/* eslint-disable react/display-name */
import React, { PropsWithChildren } from 'react'
import { Location, NavigateFunction, useLocation, useNavigate } from 'react-router-dom'

export type RouterPropsType = {
  location: Location
  navigate: NavigateFunction
}

export function withRouter(Child: any) {
  return (props: PropsWithChildren<any>) => {
    const location = useLocation()
    const navigate = useNavigate()
    return <Child {...props} navigate={navigate} location={location} />
  }
}
