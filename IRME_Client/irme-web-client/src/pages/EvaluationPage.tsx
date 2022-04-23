import React, { Component } from 'react'
import { Navigate } from 'react-router-dom'
import { MainLayout } from '../layouts/MainLayout'
import { withRouter, RouterPropsType } from '../router/withRouter'

class EvaluationPageComponent extends Component<RouterPropsType> {
  private _paramsExtractedWithSuccess = false
  private readonly _categoryId: number
  private readonly _organisationId: number

  constructor(props: RouterPropsType) {
    super(props)
    const paramResolver = new URLSearchParams(this.props.location.search)
    this._categoryId = Number(paramResolver.get('_categoryId'))
    this._organisationId = Number(paramResolver.get('_organisationId'))

    if (this._categoryId && this._organisationId) {
      this._paramsExtractedWithSuccess = true
    }
  }

  render() {
    if (!this._paramsExtractedWithSuccess) {
      return <Navigate to={'/?error=1'} />
    }

    return (
      <MainLayout>
        <div className="container">
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
        </div>
      </MainLayout>
    )
  }
}

export const EvaluationPage = withRouter(EvaluationPageComponent)
