import React, { Fragment } from 'react'
import { useSelector } from 'react-redux'
import { HashRouter, Route, Routes } from 'react-router-dom'
import { CategoriesPage } from '../pages/CategoriesPage'
import { LoginPage } from '../pages/LoginPage'
import { MainPage } from '../pages/MainPage'
import { GlobalStateType } from '../store/store'

export const AppRouter = () => {
  const isAuthenticated = useSelector((state: GlobalStateType) => {
    return state.auth.isAuthenticated
  })

  return (
    <Fragment>
      <HashRouter>
        <Routes>
          <Route path="/" element={<MainPage />} />
          <Route path="/categories/*" element={<CategoriesPage />} />
          <Route path="/login/*" element={<LoginPage />} />
          <Route path="*" element={<MainPage />} />
        </Routes>
      </HashRouter>
    </Fragment>
  )
}
