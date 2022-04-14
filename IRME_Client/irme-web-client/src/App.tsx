import React, { ReactElement } from 'react'
import { AppRouter } from './router/AppRouter'
import { store } from './store/store'
import { Provider } from 'react-redux'
import { BaseApiProvider } from './api/BaseApiProvider'

import 'materialize-css/dist/css/materialize.min.css'
import 'materialize-css/dist/js/materialize.min.js'
import './App.css'

BaseApiProvider.config = {
  errorCodeHandler: {
    2: () => {
      console.log('auth error')
    },
  },
}

function App(): ReactElement {
  return (
    <Provider store={store}>
      <AppRouter />
    </Provider>
  )
}
export default App
