import React from 'react'
import { useSelector } from 'react-redux'
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom'
import { GlobalAppWrapper } from '../components/GlobalAppWrapper'
import { URLErrorWrapper } from '../components/URLErrorWrapper'
import { CategoriesPage } from '../pages/CategoriesPage'
import { EvaluationPage } from '../pages/EvaluationPage'
import { LoginPage } from '../pages/LoginPage'
import { MainPage } from '../pages/MainPage'
import { GlobalStateType } from '../store/store'

function SessionExpiredComponent() {
  return (
    <URLErrorWrapper>
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
    <GlobalAppWrapper>
      <Router>
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
      </Router>
    </GlobalAppWrapper>
  )
}
