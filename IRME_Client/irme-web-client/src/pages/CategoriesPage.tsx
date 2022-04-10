import React, { ReactElement } from 'react'
import { Loader } from '../components/Loader'
import { MainLayout } from '../layouts/MainLayout'

const items = [1, 2, 3, 4, 5, 6, 7]

export function CategoriesPage(): ReactElement {
  const elements = items.map((el) => {
    return (
      <div key={el} className="col s12 m4">
        <div className="card">
          <div className="card-image">
            <img src="sa_categories/authorization.png" />
            <span className="card-title">Card Title</span>
          </div>
          <div className="card-content">
            <p>
              I am a very simple card. I am good at containing small bits of information. I am convenient because I
              require little markup to use effectively.
            </p>
          </div>
          <div className="card-action">
            <a href="#">This is a link</a>
          </div>
        </div>
      </div>
    )
  })

  return (
    <MainLayout>
      <div style={{ marginTop: '30px' }} className="container">
        <Loader />
      </div>
      <div className="container">
        <div className="row">{elements}</div>
      </div>
    </MainLayout>
  )
}
