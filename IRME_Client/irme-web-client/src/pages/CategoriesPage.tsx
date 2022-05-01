import React, { ReactElement, useEffect, useState } from 'react'
import { useSelector } from 'react-redux'
import { Link } from 'react-router-dom'
import { OrganisationProvider } from '../api/OrganisationProvider'
import { ISACategory, SACategoryProvider } from '../api/SACategoryProvider'
import { FixedMultilineSpan } from '../components/FixedMultilineSpan'
import { FullScreenLoader } from '../components/FullScreenLoader'
import { MainLayout } from '../layouts/MainLayout'
import { GlobalStateType } from '../store/store'
const M = require('materialize-css/dist/js/materialize.min.js')

export function CategoriesPage(): ReactElement {
  const [previewCategories, setPreviewCategories] = useState<Array<ISACategory>>([])
  const { isAuthenticated, token } = useSelector((state: GlobalStateType) => {
    const { isAuthenticated, token } = state.auth
    return { isAuthenticated, token }
  })
  const [selectedCategoryId, setSelectedCategoryId] = useState<number>(-1)
  const [organisations, setOrganisations] = useState<Record<number, string>>({})

  useEffect(() => {
    const modalNode = document.querySelector('#orgsModalId')
    if (modalNode) {
      M.Modal.init(modalNode, {
        inDuration: 250,
        outDuration: 250,
        opacity: 0.5,
        startingTop: '4%',
        endingTop: '10%',
      })
    }

    return () => {
      const modalNode = document.querySelector('#orgsModalId')
      if (modalNode) {
        const instance = M.Modal.getInstance(modalNode)
        instance && instance.destroy()
      }
    }
  }, [])

  useEffect(() => {
    const categoryProvider = new SACategoryProvider()
    const organisationProvider = new OrganisationProvider()

    if (isAuthenticated && token !== null) {
      categoryProvider.getSecurityAssessmentCategories(token).then((d) => {
        if (Array.isArray(d)) {
          setPreviewCategories(d)
        }
      })

      organisationProvider.userOrganisations(token).then((d) => {
        if (Array.isArray(d)) {
          const elements = d.reduce((acc, current) => {
            acc[current.id] = current.name
            return acc
          }, {} as Record<number, string>)
          setOrganisations(elements)
        }
      })
    }
  }, [])

  const elements = previewCategories.map((el) => {
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
            <a
              onClick={() => setSelectedCategoryId(el.categroyId)}
              href="#orgsModalId"
              className="waves-effect waves-light btn blue darken-2 modal-trigger "
            >
              Evaluează
            </a>
          </div>
        </div>
      </div>
    )
  })

  return (
    <MainLayout>
      {previewCategories.length === 0 && <FullScreenLoader />}

      <div className="container">
        <div style={{ marginTop: '20px' }} className="row">
          {elements}
        </div>

        {/* Modal element */}
        <div id="orgsModalId" className="modal">
          <div className="modal-content grey-text text-darken-4">
            <h4>Lista de organizații</h4>
            <p>Selectați o organizație asignată dvs. pentru care va fi realizată rvaluarea de riscuri</p>
            <section>
              <div className="collection">
                {Object.keys(organisations).map((k) => (
                  <Link
                    to={`/evaluation?_categoryId=${selectedCategoryId}&_organisationId=${k}`}
                    key={k}
                    className="btn-flat waves-effect waves-teal collection-item"
                  >
                    {organisations[Number(k)]}
                  </Link>
                ))}
              </div>
            </section>
          </div>
          <div className="modal-footer" style={{ padding: '15px' }}>
            <p>Platforma dată garantează că datele ce introduse de dvs. se vor păstra securizat.</p>
          </div>
        </div>
      </div>
    </MainLayout>
  )
}
