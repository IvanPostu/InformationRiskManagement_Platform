import React, { Fragment, PropsWithChildren, ReactElement } from 'react'
import { Provider } from 'react-redux'
import { store } from './store'

type ReduxWrapperPropsType = PropsWithChildren<unknown>

export default function ReduxWrapper(props: ReduxWrapperPropsType): ReactElement {
  return (
    <Fragment>
      <Provider store={store}>{props.children}</Provider>
    </Fragment>
  )
}
