import React, { Fragment, PropsWithChildren, ReactElement } from 'react'
import { Provider } from 'react-redux'
import { store } from './store'

// eslint-disable-next-line @typescript-eslint/no-explicit-any
type ReduxWrapperPropsType = PropsWithChildren<any>

export default function ReduxWrapper(props: ReduxWrapperPropsType): ReactElement {
  return (
    <Fragment>
      <Provider store={store}>{props.children}</Provider>
    </Fragment>
  )
}
