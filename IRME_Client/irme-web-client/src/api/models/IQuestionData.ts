export interface IQuestionData {
  questionId: number
  parentQuestionAnswerId: number
  hasMultipleAnswers: boolean
  questionWeight: number

  question: string

  answers: Array<{
    answerId: number
    questionAnswerId: number
    answer: string
  }>
}
