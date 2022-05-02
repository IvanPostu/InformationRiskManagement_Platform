const M = require('materialize-css/dist/js/materialize.min.js')

interface IToastMessageProps {
  message: string
  classes?: string
  type?: 'error' | 'success' | 'default'
}

export function toastMessage(props: IToastMessageProps): void {
  const defaultClasses = props.classes || ''
  let textClass = ''

  switch (props.type) {
    case 'error':
      textClass = 'red-text text-darken-3'
      break
    case 'success':
      textClass = 'green-text text-darken-3'
      break
    case 'default':
    default:
      textClass = ' '
  }

  const html = `
      <b class="${textClass}">${props.message}</b>
  `

  M.toast({
    html,
    classes: 'black-text blue-grey lighten-5' + defaultClasses,
  })
}
