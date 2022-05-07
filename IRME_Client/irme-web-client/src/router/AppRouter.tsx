import React from 'react'
import { useSelector } from 'react-redux'
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom'
import { GlobalAppWrapper } from '../components/GlobalAppWrapper'
import { URLErrorWrapper } from '../components/URLErrorWrapper'
import CategoriesEvaluationsSummaryPage from '../pages/CategoriesEvaluationsSummaryPage'
import CategoriesPage from '../pages/CategoriesPage'
import CurrentOrganisationEvaluationsPage from '../pages/CurrentOrganisationEvaluationsPage'
import EvaluationPage from '../pages/EvaluationPage'
import EvaluationReportPage from '../pages/EvaluationReportPage'
import LoginPage from '../pages/LoginPage'
import MainPage from '../pages/MainPage'
import OrganisationsPage from '../pages/OrganisationsPage'
import { GlobalStateType } from '../store/store'

function SessionExpiredComponent() {
  return (
    <URLErrorWrapper>
      <MainPage />
    </URLErrorWrapper>
  )
}

function requireAuth(element: JSX.Element): JSX.Element {
  const isAuthenticated = useSelector((state: GlobalStateType) => {
    return state.auth.isAuthenticated
  })

  if (!isAuthenticated) {
    return <SessionExpiredComponent />
  }

  return element
}

export const AppRouter = () => {
  return (
    <GlobalAppWrapper>
      <Router>
        <Routes>
          <Route path="/" element={<MainPage />} />
          <Route path="/error/*" element={<SessionExpiredComponent />} />
          <Route path="/categories/*" element={requireAuth(<CategoriesPage />)} />
          <Route path="/organisations/*" element={requireAuth(<OrganisationsPage />)} />
          <Route path="/categoriesEvaluationsSummary/*" element={requireAuth(<CategoriesEvaluationsSummaryPage />)} />
          <Route
            path="/currentOrganisationEvaluations/*"
            element={requireAuth(<CurrentOrganisationEvaluationsPage />)}
          />

          <Route path="/evaluationReport/*" element={requireAuth(<EvaluationReportPage />)} />
          <Route path="/evaluation/*" element={requireAuth(<EvaluationPage />)} />
          <Route path="/login/*" element={<LoginPage />} />
          <Route path="*" element={<MainPage />} />
        </Routes>
      </Router>
    </GlobalAppWrapper>
  )
}
