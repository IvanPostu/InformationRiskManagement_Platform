import React, { ReactElement, SyntheticEvent, useCallback } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import { Navigate } from 'react-router-dom'
import { MainLayout } from '../layouts/MainLayout'
import { authenticate } from '../store/auth/authActionCreators'
import { AppDispatch, GlobalStateType } from '../store/store'

export default function LoginPage(): ReactElement {
  const dispatch = useDispatch<AppDispatch>()

  const isAuthenticated = useSelector((state: GlobalStateType) => {
    return state.auth.isAuthenticated
  })

  const onSubmit = useCallback(
    (e: SyntheticEvent) => {
      e.preventDefault()
      const email = document.querySelector<HTMLInputElement>('#emailInputId')?.value
      const password = document.querySelector<HTMLInputElement>('#passwordInputId')?.value

      if (email && password) {
        dispatch(authenticate(email, password))
      }
    },
    [dispatch]
  )

  if (isAuthenticated) {
    return <Navigate to="/" replace />
  }

  return (
    <MainLayout>
      <div className="container">
        <div style={{ marginTop: '20px' }} className="row ">
          <div className="col s12 m6 offset-m3 ">
            <div className="card  grey lighten-5">
              <div className="card-content black-text">
                <span className="card-title">
                  <h5>Autentificare</h5>
                </span>
                <form onSubmit={onSubmit} style={{ margin: '20px 0' }} className="row">
                  <div className="input-field col s10">
                    <input id="emailInputId" type="text" className="validate" />
                    <label className="active" htmlFor="emailInputId">
                      Email:
                    </label>
                  </div>
                  <div className="input-field col s10">
                    <input id="passwordInputId" type="password" className="validate" />
                    <label className="active" htmlFor="passwordInputId">
                      Parolă:
                    </label>
                  </div>
                  <div className="input-field col s10">
                    <p>
                      <label>
                        <input type="checkbox" className="filled-in" />
                        <span>Sesiunea de autentificare persistentă</span>
                      </label>
                    </p>
                  </div>

                  <div className="input-field col s10">
                    <button type="submit" className="waves-effect waves-light btn blue-grey darken-2">
                      Autentifică
                    </button>
                  </div>
                </form>
              </div>
              <div className="card-action">
                <a className="blue-text" href="#">
                  Resetare parolă
                </a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </MainLayout>
  )
}
