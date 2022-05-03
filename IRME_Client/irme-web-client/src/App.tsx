import React, { ReactElement } from 'react'
import { AppRouter } from './router/AppRouter'
import { store } from './store/store'
import { Provider } from 'react-redux'
import { BaseApiProvider } from './api/BaseApiProvider'

import 'materialize-css/dist/css/materialize.min.css'
import 'materialize-css/dist/js/materialize.min.js'
import './App.css'
import { authActionTypeConstants, DeauthenticateActionType } from './store/auth/authTypes'

BaseApiProvider.config = {
  errorCodeHandler: {
    2: () => {
      store.dispatch({ type: authActionTypeConstants.DEAUTHENTICATE_USER } as DeauthenticateActionType)
    },
  },
}

function App(): ReactElement {
  return (
    <React.StrictMode>
      <Provider store={store}>
        <AppRouter />
      </Provider>
    </React.StrictMode>
  )
}
export default App
