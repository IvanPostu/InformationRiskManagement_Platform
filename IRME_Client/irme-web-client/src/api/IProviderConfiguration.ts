export interface IProviderConfiguration {
  errorCodeHandler: Record<number, () => void>
}
