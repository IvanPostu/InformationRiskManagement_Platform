import React, { ReactElement } from 'react'
import 'materialize-css/dist/css/materialize.min.css'
import 'materialize-css/dist/js/materialize.min.js'
import './App.css'
import ReduxWrapper from './store/ReduxWrapper'
import { AppRouter } from './router/AppRouter'

function App(): ReactElement {
  return (
    <ReduxWrapper>
      <AppRouter />
    </ReduxWrapper>
  )
}

export default App
