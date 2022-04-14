import React, { Fragment } from 'react'
import { useSelector } from 'react-redux'
import { HashRouter, Route, Routes } from 'react-router-dom'
import { CategoriesPage } from '../pages/CategoriesPage'
import { LoginPage } from '../pages/LoginPage'
import { MainPage } from '../pages/MainPage'
import { GlobalStateType } from '../store/store'

function AuthElement(element: JSX.Element, isAuthenticated: boolean): JSX.Element {
  if (!isAuthenticated) {
    return <MainPage />
  }

  return element
}

export const AppRouter = () => {
  const isAuthenticated = useSelector((state: GlobalStateType) => {
    return state.auth.isAuthenticated
  })

  return (
    <Fragment>
      <HashRouter>
        <Routes>
          <Route path="/" element={<MainPage />} />
          <Route path="/categories/*" element={AuthElement(<CategoriesPage />, isAuthenticated)} />
          <Route path="/login/*" element={<LoginPage />} />
          <Route path="*" element={<MainPage />} />
        </Routes>
      </HashRouter>
    </Fragment>
  )
}
