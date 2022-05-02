import React, { FC, PropsWithChildren, ReactElement } from 'react'
import { Modal, ModalProps } from 'react-materialize'

const CustomModal = Modal as FC<ModalProps & PropsWithChildren<unknown>>

type GenericModalPropsType = {
  content: ReactElement
  footerContent: ReactElement
  id: string
}

export function GenericModal(props: GenericModalPropsType) {
  const { content, id, footerContent } = props

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
      bottomSheet={false}
      fixedFooter={false}
      id={id}
      open={false}
      actions={[footerContent]}
      root={document.body}
    >
      <div className="grey-text text-darken-4">{content}</div>
    </CustomModal>
  )
}
