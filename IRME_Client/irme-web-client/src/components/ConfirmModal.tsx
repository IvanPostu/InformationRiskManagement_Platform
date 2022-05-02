import React, { FC, PropsWithChildren } from 'react'
import { Button, ButtonProps, Modal, ModalProps } from 'react-materialize'

const CustomModal = Modal as FC<ModalProps & PropsWithChildren<unknown>>

type ConfirmModalPropsType = {
  title: string
  content: string
  onYesClick: () => void
  id: string
}

export function ConfirmModal(props: ConfirmModalPropsType) {
  const { content, id, onYesClick, title } = props

  return (
    <CustomModal
      options={{
        dismissible: true,
        endingTop: '10%',
        inDuration: 250,
        opacity: 0.5,
        outDuration: 250,
        preventScrolling: true,
        startingTop: '4%',
      }}
      header={title}
      bottomSheet={false}
      fixedFooter={false}
      id={id}
      open={false}
      actions={[
        <Button onClick={onYesClick} key={1} flat modal="close" node="button" waves="green">
          Da
        </Button>,
        <Button key={2} flat modal="close" node="button" waves="green">
          Nu
        </Button>,
      ]}
      root={document.body}
    >
      <span className="grey-text text-darken-4">
        <b>{content}</b>
      </span>
    </CustomModal>
  )
}

type ConfirmModalTriggerButtonPropsType = ButtonProps & {
  href: string
  type: string
}

export function ConfirmModalTriggerButton(props: ConfirmModalTriggerButtonPropsType) {
  return <Button {...props} />
}
