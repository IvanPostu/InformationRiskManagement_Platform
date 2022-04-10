import React, { ReactElement } from 'react'
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

  // const { data, isLoading, error } = useQuery('launches', () => {
  //   return fetch(endpoint, {
  //     method: 'POST',
  //     headers: { 'Content-Type': 'application/json' },
  //     body: JSON.stringify({ query: AUTH_USER }),
  //   })
  //     .then((response) => {
  //       if (response.status >= 400) {
  //         throw new Error('Error fetching data')
  //       } else {
  //         return response.json()
  //       }
  //     })
  //     .then((data) => data.data)
  // })

  // if (isLoading) return <div>{'Loading...'}</div>
  // if (error) return <pre>{'error'}</pre>

  // console.log(data)

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
