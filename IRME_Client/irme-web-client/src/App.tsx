import React from 'react'
import { HashRouter, Route, Switch } from 'react-router-dom'
import 'materialize-css/dist/css/materialize.min.css'
import 'materialize-css/dist/js/materialize.min.js'
import './App.css'
import { CategoriesPage } from './pages/CategoriesPage'
import { MainPage } from './pages/MainPage'
import ReduxWrapper from './store/ReduxWrapper'

function App() {
  return (
    <ReduxWrapper>
      <HashRouter>
        <Switch>
          <Route exact path="/" component={MainPage} />
          <Route exact path="/categories" component={CategoriesPage} />
          <Route path="*" component={MainPage} />
        </Switch>
      </HashRouter>
    </ReduxWrapper>
  )
}

export default App
