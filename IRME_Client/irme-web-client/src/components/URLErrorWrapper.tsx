import React, { Fragment, PropsWithChildren, useEffect, useState } from 'react'
import { Navigate, useNavigate } from 'react-router-dom'
const M = require('materialize-css/dist/js/materialize.min.js')

export function URLErrorWrapper(props: PropsWithChildren<unknown>) {
  const nodeId = 'errorMModalId'

  useEffect(() => {
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
          <h4 className="deep-orange-text text-accent-4">
            <b>Eroare </b>
          </h4>
          <p>A avut loc o eroare de sistem, la moment are loc procesul de soluționare...</p>
          <a
            onClick={() => {
              const modalNode = document.querySelector('#' + nodeId)
              if (modalNode) {
                const instance = M.Modal.getInstance(modalNode)
                instance && instance.close()
                instance && instance.destroy()
              }
            }}
            className="waves-effect waves-light btn right"
          >
            OK
          </a>
        </div>
        <div className="modal-footer grey-text text-darken-1" style={{ padding: '15px' }}>
          <p>Rugăm să contactați administratorul sistemului. Cu respect...</p>
        </div>
      </div>
    </Fragment>
  )
}
