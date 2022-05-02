import React, { Fragment, PropsWithChildren, useEffect, useState } from 'react'
import { useLocation } from 'react-router-dom'
const M = require('materialize-css/dist/js/materialize.min.js')

interface IErrorState {
  title: string
  description: string
  footerText: string
}

const defaultError: IErrorState = {
  description: 'A avut loc o eroare de sistem, la moment are loc procesul de soluționare...',
  footerText: 'Rugăm să contactați administratorul sistemului. Cu respect...',
  title: 'Eroare',
}

const sessionExpiredError: IErrorState = {
  description: 'Sesiune dvs. a expirat, vă rugăm să vă reautentificați.',
  footerText: '',
  title: 'Sesiunea de autentificare a expirat',
}

type URLErrorWrapperPropsType = PropsWithChildren<unknown>

export function URLErrorWrapper(props: URLErrorWrapperPropsType) {
  const nodeId = 'errorMModalId'
  const [state, setState] = useState<IErrorState>(defaultError)
  const location = useLocation()

  useEffect(() => {
    let errorCode = 1
    try {
      const pathErrorCode = Number(location.pathname.split('/error/')[1])

      if (pathErrorCode) {
        errorCode = pathErrorCode
      }
    } catch {}

    if (errorCode === 2) {
      setState(sessionExpiredError)
    }

    const modalNode = document.querySelector('#' + nodeId)
    if (modalNode) {
      M.Modal.init(modalNode, {
        inDuration: 250,
        outDuration: 250,
        opacity: 0.5,
        startingTop: '4%',
        endingTop: '10%',
      })

      const instance = M.Modal.getInstance(modalNode)
      instance && instance.open()
    }

    return () => {
      const modalNode = document.querySelector('#' + nodeId)
      if (modalNode) {
        const instance = M.Modal.getInstance(modalNode)
        instance && instance.close()
        instance && instance.destroy()
      }
    }
  }, [])

  return (
    <Fragment>
      {props.children}
      {/* Modal element */}
      <div id={nodeId} className="modal">
        <div className="modal-content grey-text text-darken-4">
          <h4 className=" red-text text-darken-3">
            <b>{state.title}</b>
          </h4>
          <p>{state.description}</p>
          <a
            onClick={() => {
              const modalNode = document.querySelector('#' + nodeId)
              if (modalNode) {
                const instance = M.Modal.getInstance(modalNode)
                instance && instance.close()
              }
            }}
            className="waves-effect waves-light btn right blue darken-2"
          >
            OK
          </a>
        </div>
        <div className="modal-footer grey-text text-darken-1" style={{ padding: '15px' }}>
          <p>{state.footerText}</p>
        </div>
      </div>
    </Fragment>
  )
}
