import React, { Fragment } from 'react'
import { HashRouter, Route, Routes } from 'react-router-dom'
import { CategoriesPage } from '../pages/CategoriesPage'
import { MainPage } from '../pages/MainPage'

export const AppRouter = () => {
  return (
    <Fragment>
      <HashRouter>
        <Routes>
          <Route path="/" element={<MainPage />} />
          <Route path="/categories/*" element={<CategoriesPage />} />
          <Route path="*" element={<MainPage />} />
        </Routes>
      </HashRouter>
    </Fragment>
  )
}
