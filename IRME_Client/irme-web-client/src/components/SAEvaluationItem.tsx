import React, { Fragment } from 'react'
import { CardPanel } from 'react-materialize'
import { IQuestionData } from '../api/models/IQuestionData'

type SAEvaluationItemPropsType = IQuestionData & {
  questionAnswerIds: Set<number>
  onSelect: (questionId: number, answerId: number, questionAnswerId: number) => void
}

type AnswersPropsType = {
  answers: IQuestionData['answers']
  questionAnswerIds: Set<number>
  questionId: number
  onSelect: (questionId: number, answerId: number, questionAnswerId: number) => void
}

function AnswersUnique(props: AnswersPropsType) {
  const { answers, questionId, questionAnswerIds, onSelect } = props
  return (
    <section>
      {answers.map((answer) => (
        <p key={answer.answerId}>
          <label>
            <input
              onChange={() => onSelect(questionId, answer.answerId, answer.questionAnswerId)}
              checked={questionAnswerIds.has(answer.questionAnswerId)}
              name={`group1_${questionId}`}
              type="radio"
            />
            <span className="grey-text text-darken-2">{answer.answer}</span>
          </label>
        </p>
      ))}
    </section>
  )
}

function AnswersMultiple(props: AnswersPropsType) {
  const { answers, questionId, questionAnswerIds, onSelect } = props
  return (
    <section>
      {answers.map((answer) => (
        <p key={answer.answerId}>
          <label>
            <input
              onChange={() => onSelect(questionId, answer.answerId, answer.questionAnswerId)}
              checked={questionAnswerIds.has(answer.questionAnswerId)}
              name={`group1_${questionId}`}
              type="checkbox"
            />
            <span className="grey-text text-darken-2">{answer.answer}</span>
          </label>
        </p>
      ))}
    </section>
  )
}

export function SAEvaluationItem(props: SAEvaluationItemPropsType) {
  const { answers, hasMultipleAnswers, parentQuestionAnswerId, question, questionId, questionAnswerIds, onSelect } =
    props

  const answersElements = hasMultipleAnswers ? (
    <AnswersMultiple
      onSelect={onSelect}
      answers={answers}
      questionId={questionId}
      questionAnswerIds={questionAnswerIds}
    />
  ) : (
    <AnswersUnique
      onSelect={onSelect}
      answers={answers}
      questionId={questionId}
      questionAnswerIds={questionAnswerIds}
    />
  )

  const isVisible = parentQuestionAnswerId === 0 || questionAnswerIds.has(parentQuestionAnswerId)

  if (!isVisible) return <Fragment />

  return (
    <CardPanel className="blue-grey lighten-5" style={{ marginLeft: parentQuestionAnswerId !== 0 ? '30px' : '0' }}>
      <span className="grey-text text-darken-4">
        <b>{question}</b>
        <section>{answersElements}</section>
      </span>
    </CardPanel>
  )
}
