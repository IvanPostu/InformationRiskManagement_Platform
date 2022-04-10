import { Action } from 'redux'

export enum counterActionTypeConstants {
  INCREMENT = '@counter/INCREMENT',
  DECREMENT = '@counter/DECREMENT',
  SET_VALUE = '@counter/SET_VALUE',
}

export type CounterStateType = {
  value: number
}

export type IncrementActionType = Action<counterActionTypeConstants.INCREMENT>

export type DecrementActionType = Action<counterActionTypeConstants.DECREMENT>

export type SetValueActionType = Action<counterActionTypeConstants.SET_VALUE> & {
  payload: {
    value: number
  }
}

export type CounterRootActionType = IncrementActionType | DecrementActionType | SetValueActionType
