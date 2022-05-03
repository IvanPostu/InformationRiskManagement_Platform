export interface IEvaluationReport {
  maxCategoryWeight: number
  totalProcessWeight: number

  expectedProcessWeight: number

  items: Array<{
    questionId: number
    answerId: number
    question: number
    answer: string
    description: string
  }>
}
