import { Reducer } from 'redux'
import { CounterStateType, counterActionTypeConstants as T, CounterRootActionType } from './counterTypes'

const initialState: CounterStateType = {
  value: 0,
}

export const counterReducer: Reducer<CounterStateType, CounterRootActionType> = (
  state: CounterStateType = initialState,
  action: CounterRootActionType
) => {
  switch (action.type) {
    case T.INCREMENT:
      return { ...state, value: state.value + 1 }
    case T.DECREMENT:
      return { ...state, value: state.value - 1 }
    case T.SET_VALUE:
      return { ...state, value: action.payload.value }
    default:
      return state
  }
}
