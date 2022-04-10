import { ThunkDispatch } from 'redux-thunk'
import { GlobalStateType } from '../store'
import { counterActionTypeConstants, DecrementActionType, IncrementActionType } from './counterTypes'

type DecrementDispatchType = ThunkDispatch<GlobalStateType, void, DecrementActionType | IncrementActionType>
export function decrement() {
  return (dispatch: DecrementDispatchType) => {
    new Promise((resolve) => {
      dispatch({ type: counterActionTypeConstants.DECREMENT } as DecrementActionType)
      resolve(1)
    })
  }
}
