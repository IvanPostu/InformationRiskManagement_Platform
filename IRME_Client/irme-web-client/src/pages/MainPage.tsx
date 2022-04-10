import React from 'react'
import { useDispatch, useSelector } from 'react-redux'
import { MainLayout } from '../layouts/MainLayout'
import { decrement } from '../store/counter/counterActionCreators'
import { counterActionTypeConstants, IncrementActionType, SetValueActionType } from '../store/counter/counterTypes'
import { AppDispatch, GlobalStateType } from '../store/store'

export function MainPage() {
  const count = useSelector((state: GlobalStateType) => {
    return state.counter.value
  })
  const dispatch = useDispatch<AppDispatch>()

  return (
    <MainLayout>
      <div>{count}</div>
      <button
        onClick={() => {
          dispatch({ type: counterActionTypeConstants.INCREMENT } as IncrementActionType)
        }}
      >
        Incr
      </button>
      <button
        onClick={() => {
          dispatch(decrement())
        }}
      >
        Decr
      </button>

      <button
        onClick={() => {
          dispatch({
            type: counterActionTypeConstants.SET_VALUE,
            payload: {
              value: 100,
            },
          } as SetValueActionType)
        }}
      >
        Set 100
      </button>
    </MainLayout>
  )
}
