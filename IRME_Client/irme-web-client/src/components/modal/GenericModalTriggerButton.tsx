import React from 'react'

import { Button, ButtonProps } from 'react-materialize'

const CustomButton = Button as React.FC<
  ButtonProps & {
    type?: string
  }
>

type GenericModalTriggerButtonPropsType = ButtonProps & {
  href: string
  type?: string
}

export function GenericModalTriggerButton(props: GenericModalTriggerButtonPropsType) {
  const defaultType = props.type || 'button'

  return <CustomButton {...props} type={defaultType} />
}
