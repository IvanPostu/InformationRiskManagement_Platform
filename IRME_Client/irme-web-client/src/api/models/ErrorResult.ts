import { IError } from './IError'

export class ErrorResult {
  private _errors: Array<IError>
  private _errorCodes: Set<number>

  constructor(errors: Array<IError>) {
    this._errors = Array.isArray(errors) ? errors : []
    this._errorCodes = new Set()

    this._errors.forEach((e) => {
      this._errorCodes.add(e.errorCode)
    })
  }

  public get errors(): Array<IError> {
    return this._errors
  }

  public containsErrorCode(code: number): boolean {
    return this._errorCodes.has(code)
  }
}
