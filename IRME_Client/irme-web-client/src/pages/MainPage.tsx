import React, { ReactElement, useEffect, useState } from 'react'
import { useSelector } from 'react-redux'
import styled from 'styled-components'
import { ISACategory, SACategoryProvider } from '../api/SACategoryProvider'
import { MainLayout } from '../layouts/MainLayout'
import { GlobalStateType } from '../store/store'

const Section = styled.section`
  margin: 15px 0;
`

export function MainPage(): ReactElement {
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
          setPreviewCategories(d.filter((item, index) => index < 3))
        }
      })
    }
  }, [isAuthenticated, setPreviewCategories, token])

  const previewCategoriesElement =
    previewCategories.length > 0 ? (
      <ul className="collection">
        {previewCategories.map((category, index) => {
          const color = index === 1 ? 'red' : index === 2 ? 'green' : ''
          return (
            <li key={index} className="collection-item avatar">
              <i className={'material-icons circle ' + color}>folder</i>
              <span className="title">
                <b>{category.name}</b>
              </span>
              <p style={{ width: '95%' }}>{category.description}</p>
              <a href="#!" className="secondary-content">
                <i className="material-icons">grade</i>
              </a>
            </li>
          )
        })}
      </ul>
    ) : null

  return (
    <MainLayout>
      <div className="container">
        <Section>
          <div className="card grey-text text-darken-4">
            <div className="card-content">
              <span className="card-title">
                <h5>Categorii de evaluare a riscurilor</h5>
              </span>
              {previewCategoriesElement}
            </div>
            <div className="card-action">
              <a className="blue-text" href="#">
                Mai multe detalii...
              </a>
            </div>
          </div>
        </Section>
        <Section style={{ margin: '20px 0 30px 0' }}>
          <div className="card-panel grey-text text-darken-4">
            <h5>Descriere platformă</h5>
            Platforma de evaluare a riscurilor "Information Risk Management Expert" în companie permite utilizatorului
            de a detecta și elimina eficient riscurile curente.
            <br />
            Platforma dată are scopul de a furniza cerințe pentru crearea, implementarea, întreținerea și îmbunătățirea
            continuă a sistemului de management al companiei.
            <br />
            Utilizarea softului dat este o decizie strategică cu un impact pozitiv pentru organizatie. Crearea si
            implementarea unui sistem de management al securitatii informatiei cu ajutorul platformei date oferă
            companiei o bază fiabilă și minimizează probabilitatea de scrugere de informații sau spargere a sistemelor.
            Platforma "Information Risk Management Expert" păstrează confidențialitatea, integritatea și
            disponibilitatea informațiilor prin aplicarea unor procese de management al riscului și dă încredere
            părților interesate că riscurile sunt gestionate în mod adecvat.
            <br />
            Este important ca sistemul de management al securității informațiilor să fie parte și integrat cu acesta
            procesele organizaționale și structura generală de guvernanță și securitatea informațiilor luate în
            considerare în dezvoltarea proceselor, sistemelor informaționale și controalelor. Este de așteptat ca
            implementarea unui sistem de management al securității informațiilor să facă scala in functie de nevoile
            organizatiei. Acest standard internațional poate fi utilizat de părți interne și externe pentru evaluare
            capacitatea organizației de a-și îndeplini propriile cerințe de securitate a informațiilor.
          </div>
        </Section>
      </div>
    </MainLayout>
  )
}
