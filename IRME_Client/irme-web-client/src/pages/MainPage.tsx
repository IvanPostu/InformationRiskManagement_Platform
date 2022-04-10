import React, { ReactElement, useEffect } from 'react'
import { useQuery } from 'react-query'
import { useDispatch, useSelector } from 'react-redux'
import { AuthUserProvider } from '../api/AuthUserProvider'
import { MainLayout } from '../layouts/MainLayout'
import { decrement } from '../store/counter/counterActionCreators'
import { counterActionTypeConstants, IncrementActionType, SetValueActionType } from '../store/counter/counterTypes'
import { AppDispatch, GlobalStateType } from '../store/store'

const endpoint = '/graphql'
const AUTH_USER = `
  {
    authUser(email: "admin@mail.ru", password: "12345")
    {
        email,
        token
    }
  }
`

export function MainPage(): ReactElement {
  const count = useSelector((state: GlobalStateType) => {
    return state.counter.value
  })
  const dispatch = useDispatch<AppDispatch>()

  useEffect(() => {
    const authProvider = new AuthUserProvider()
    authProvider.authUser('admin@mail.ru', '12345').then((d) => {
      alert(d?.authUser.email)
    })
  }, [])

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
