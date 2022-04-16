import React, { Fragment } from 'react'
import { useSelector } from 'react-redux'
import { HashRouter, Route, Routes } from 'react-router-dom'
import { URLErrorWrapper } from '../components/URLErrorWrapper'
import { CategoriesPage } from '../pages/CategoriesPage'
import { EvaluationPage } from '../pages/EvaluationPage'
import { LoginPage } from '../pages/LoginPage'
import { MainPage } from '../pages/MainPage'
import { GlobalStateType } from '../store/store'

function SessionExpiredComponent() {
  return (
    <URLErrorWrapper errorCode={2}>
      <MainPage />
    </URLErrorWrapper>
  )
}

function AuthElement(element: JSX.Element, isAuthenticated: boolean): JSX.Element {
  if (!isAuthenticated) {
    return <SessionExpiredComponent />
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
          <Route path="/error/*" element={<SessionExpiredComponent />} />
          <Route path="/categories/*" element={AuthElement(<CategoriesPage />, isAuthenticated)} />

          {/* _categoryId
          _organisationId */}
          <Route path="/evaluation/*" element={AuthElement(<EvaluationPage />, isAuthenticated)} />
          <Route path="/login/*" element={<LoginPage />} />
          <Route path="*" element={<MainPage />} />
        </Routes>
      </HashRouter>
    </Fragment>
  )
}
