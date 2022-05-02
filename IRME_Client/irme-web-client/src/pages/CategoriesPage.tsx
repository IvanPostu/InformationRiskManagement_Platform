import React, { Fragment, ReactElement, useEffect, useRef, useState } from 'react'
import { useSelector } from 'react-redux'
import { useNavigate } from 'react-router-dom'
import { ISAEvaluationProvider } from '../api/ISAEvaluationProvider'
import { ErrorResult } from '../api/models/ErrorResult'
import { OrganisationProvider } from '../api/OrganisationProvider'
import { ISACategory, SACategoryProvider } from '../api/SACategoryProvider'
import { SAEvaluationProvider } from '../api/SAEvaluationProvider'
import { FixedMultilineSpan } from '../components/FixedMultilineSpan'
import { FullScreenLoader } from '../components/FullScreenLoader'
import { GenericModal, GenericModalTriggerButton } from '../components/modal'
import { MainLayout } from '../layouts/MainLayout'
import { GlobalStateType } from '../store/store'
import base64 from '../utils/base64'
import { makeId } from '../utils/makeId'
const M = require('materialize-css/dist/js/materialize.min.js')

type CategoriesPageStateType = {
  previewCategories: Array<ISACategory>
  processCreatingRequest: boolean
  selectedOrganisation: {
    organisationId: number
    organisationName: string
  }
  selectedCategory: {
    categoryName: string
    categoryId: number
  }
  organisations: Record<number, string>
}

export function CategoriesPage(): ReactElement {
  const modalId = useRef(makeId(15))
  const isMounted = useRef<boolean>(false)
  const navigate = useNavigate()

  const { token: tokenFromStore } = useSelector((state: GlobalStateType) => {
    const { token } = state.auth
    return { token }
  })
  const token = tokenFromStore || ''
  const [state, setState] = useState<CategoriesPageStateType>({
    organisations: {},
    previewCategories: [],
    processCreatingRequest: false,
    selectedOrganisation: {
      organisationId: -1,
      organisationName: '',
    },
    selectedCategory: {
      categoryId: -1,
      categoryName: '',
    },
  })

  useEffect(() => {
    isMounted.current = true
    const categoryProvider = new SACategoryProvider()
    const organisationProvider = new OrganisationProvider()

    categoryProvider.getSecurityAssessmentCategories(token).then((d) => {
      if (!isMounted.current) return

      if (Array.isArray(d)) {
        setState((prevState) => ({
          ...prevState,
          previewCategories: d,
        }))
      }
    })

    organisationProvider.userOrganisations(token).then((d) => {
      if (!isMounted.current) return

      if (Array.isArray(d)) {
        const elements = d.reduce((acc, current) => {
          acc[current.id] = current.name
          return acc
        }, {} as Record<number, string>)
        setState((prevState) => ({
          ...prevState,
          organisations: elements,
        }))
      }
    })

    return () => {
      isMounted.current = false
    }
  }, [])

  useEffect(() => {
    const { categoryId, categoryName } = state.selectedCategory
    const { organisationId, organisationName } = state.selectedOrganisation
    const { processCreatingRequest } = state

    if (processCreatingRequest) return

    const provider: ISAEvaluationProvider = new SAEvaluationProvider()

    if (organisationId !== -1 && categoryId !== -1) {
      setState((prevState) => ({
        ...prevState,
        processCreatingRequest: true,
      }))

      provider.createEvaluationProcess(token, organisationId, categoryId).then((data) => {
        if (!isMounted.current) return

        if (data !== null && !(data instanceof ErrorResult) && data > 0) {
          const link = `/evaluation?processId=${data}&categoryId=${categoryId}&organisationId=${organisationId}&organisationName=${base64.encode(
            organisationName
          )}&categoryName=${base64.encode(categoryName)}`

          navigate(link)
        }
      })
    }
  }, [state])

  const elements = state.previewCategories.map((el) => {
    return (
      <div key={el.categroyId} className="col s12 m4">
        <div style={{ height: '400px' }} className="card small">
          <div className="card-image">
            <img style={{ maxHeight: '150px' }} src={el.imageUrl} />
            <span className="card-title">{el.name}</span>
          </div>
          <div className="card-content ">
            <FixedMultilineSpan linesCount={6}>{el.description}</FixedMultilineSpan>
          </div>
          <div className="card-action">
            <GenericModalTriggerButton
              href={'#' + modalId.current}
              className="waves-effect waves-light btn blue darken-2 modal-trigger "
              style={{ zIndex: 0 }}
              onClick={() => {
                setState((prevState) => ({
                  ...prevState,
                  selectedCategory: {
                    categoryId: el.categroyId,
                    categoryName: el.name,
                  },
                }))
              }}
            >
              Evaluează
            </GenericModalTriggerButton>
          </div>
        </div>
      </div>
    )
  })

  return (
    <Fragment>
      <MainLayout>
        <GenericModal
          content={
            <Fragment>
              <h4>Lista de organizații</h4>
              <p>Selectați o organizație asignată dvs. pentru care va fi realizată rvaluarea de riscuri</p>
              <section>
                <div className="collection">
                  {Object.keys(state.organisations).map((k) => {
                    const organisationName = state.organisations[Number(k)]

                    return (
                      <a
                        onClick={() => {
                          setState((prevState) => ({
                            ...prevState,
                            selectedOrganisation: {
                              organisationId: Number(k),
                              organisationName: organisationName,
                            },
                          }))

                          const modalNode = document.querySelector('#' + modalId.current)
                          if (modalNode) {
                            const instance = M.Modal.getInstance(modalNode)
                            instance && instance.close()
                          }
                        }}
                        key={k}
                        className="btn-flat waves-effect waves-teal collection-item"
                      >
                        {organisationName}
                      </a>
                    )
                  })}
                </div>
              </section>
            </Fragment>
          }
          id={modalId.current}
          footerContent={
            <div className="modal-footer" style={{ padding: '0' }}>
              <p>Platforma dată garantează că datele ce introduse de dvs. se vor păstra securizat.</p>
            </div>
          }
        />

        <div className="container">
          <div style={{ marginTop: '20px' }} className="row">
            {elements}
          </div>
        </div>
      </MainLayout>

      {state.processCreatingRequest && <FullScreenLoader zIndex={99} />}
    </Fragment>
  )
}
