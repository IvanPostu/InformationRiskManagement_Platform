import { Action, applyMiddleware, createStore } from 'redux'
import rootReducer from './rootReducer'
import { composeWithDevTools } from '@redux-devtools/extension'
import { isDev } from '../helpers/isDev'
import reduxThunk, { ThunkDispatch } from 'redux-thunk'

const enhancer = isDev() ? composeWithDevTools(applyMiddleware(reduxThunk)) : applyMiddleware(reduxThunk)
const store = createStore(rootReducer, enhancer)

export { store }
export type GlobalStateType = ReturnType<typeof rootReducer>

type IAppActions = AnyAction //<-- merge here other actions
export type AppDispatch = ThunkDispatch<GlobalStateType, void, IAppActions>

export interface AnyAction extends Action {
  // eslint-disable-next-line @typescript-eslint/no-explicit-any
  [extraProps: string]: any
}
