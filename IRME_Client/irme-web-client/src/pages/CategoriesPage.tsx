import React, { ReactElement, useEffect, useState } from 'react'
import { useSelector } from 'react-redux'
import { ISACategory, SACategoryProvider } from '../api/SACategoryProvider'
import { FixedMultilineSpan } from '../components/FixedMultilineSpan'
import { MainLayout } from '../layouts/MainLayout'
import { GlobalStateType } from '../store/store'

export function CategoriesPage(): ReactElement {
  const [previewCategories, setPreviewCategories] = useState<Array<ISACategory>>([])
  const { isAuthenticated, token } = useSelector((state: GlobalStateType) => {
    const { isAuthenticated, token } = state.auth
    return { isAuthenticated, token }
  })

  useEffect(() => {
    const provider = new SACategoryProvider()

    if (isAuthenticated && token !== null) {
      provider.getSecurityAssessmentCategories(token).then((d) => {
        if (Array.isArray(d)) {
          setPreviewCategories(d)
        }
      })
    }
  }, [isAuthenticated, setPreviewCategories, token])

  const elements = previewCategories.map((el) => {
    return (
      <div key={el.categroyId} className="col s12 m4">
        <div style={{ height: '400px' }} className="card small">
          <div className="card-image">
            <img style={{ maxWidth: '270px', maxHeight: '150px' }} src={el.imageUrl} />
            <span className="card-title">{el.name}</span>
          </div>
          <div className="card-content ">
            <FixedMultilineSpan linesCount={6}>{el.description}</FixedMultilineSpan>
          </div>
          <div className="card-action">
            <a className="blue-text" href="#">
              Evaluare conform categoriei
            </a>
          </div>
        </div>
      </div>
    )
  })

  return (
    <MainLayout>
      <div className="container">
        <div style={{ marginTop: '20px' }} className="row">
          {elements}
        </div>
      </div>
    </MainLayout>
  )
}
