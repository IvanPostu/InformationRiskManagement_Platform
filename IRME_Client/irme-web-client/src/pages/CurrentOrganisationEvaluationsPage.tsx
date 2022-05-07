import React, { useEffect, useState } from 'react'
import { CardPanel } from 'react-materialize'
import { useSelector } from 'react-redux'
import { Link, useLocation } from 'react-router-dom'
import { ErrorResult } from '../api/models/ErrorResult'
import { IEvaluationProcess } from '../api/models/IEvaluationProcess'
import { SAEvaluationProvider } from '../api/SAEvaluationProvider'
import { MainLayout } from '../layouts/MainLayout'
import { GlobalStateType } from '../store/store'
import base64 from '../utils/base64'
import { buildEvaluationLink } from '../utils/buildEvaluationLink'
import { buildEvaluationReportPageLink } from '../utils/buildEvaluationReportPageLink'

type CurrentOrganisationEvaluationsPageStateType = {
  organisationName: string
  organisationId: number
  evaluations: Array<IEvaluationProcess>
}

function mapStatusCodeToString(statusCode: number): string {
  //0:open, 1:completed, 2:force_closed
  switch (statusCode) {
    case 0:
      return 'În proces'
    case 1:
      return 'Finalizat'
    case 3:
      return 'Finalizat forțat'
    default:
      return '------'
  }
}

export default function CurrentOrganisationEvaluationsPage() {
  const location = useLocation()
  const token = useSelector((state: GlobalStateType) => {
    return state.auth.token || ''
  })
  const [state, setState] = useState<CurrentOrganisationEvaluationsPageStateType>({
    organisationId: -1,
    organisationName: '',
    evaluations: [],
  })

  useEffect(() => {
    let isMounted = true
    const paramResolver = new URLSearchParams(location.search)
    const provider = new SAEvaluationProvider()
    const organisationId = Number(paramResolver.get('organisationId'))
    const organisationName = base64.decode(paramResolver.get('organisationName') || '')

    setState((prevState) => ({ ...prevState, organisationId, organisationName }))

    provider.getEvaluationProcesses(token, organisationId).then((data) => {
      if (!isMounted) return

      if (data !== null && !(data instanceof ErrorResult)) {
        setState((prevState) => ({ ...prevState, evaluations: data }))
      }
    })

    return () => {
      isMounted = false
    }
  }, [])

  const items = state.evaluations.map((e) => {
    return (
      <div key={e.processId} className="collection-item   indigo-text text-darken-4">
        <div className="row">
          <div className="col m4 s12">
            Proces de evaluare #{e.processId} <br />
            Categorie: {e.categoryName} <br />
            Statut: {mapStatusCodeToString(e.statusCode)}
          </div>
          <div className="col m4 s12">
            Utilizator: {e.userEmail}
            <br />
            Data: {e.created}
          </div>
          <div className="col m4 s12">
            {e.statusCode === 0 && <a className="waves-effect waves-light btn red darken-4">Finalizează forțat</a>}
            {e.statusCode === 0 && (
              <Link
                to={buildEvaluationLink(
                  e.processId,
                  e.categoryId,
                  e.organisationId,
                  e.organisationName,
                  e.categoryName
                )}
                style={{ marginTop: '5px' }}
                className="waves-effect waves-light btn green darken-4"
              >
                Continuă evaluarea
              </Link>
            )}
            {e.statusCode > 0 && (
              <Link
                to={buildEvaluationReportPageLink(
                  e.processId,
                  e.categoryId,
                  e.organisationId,
                  e.organisationName,
                  e.categoryName
                )}
                style={{ marginTop: '5px' }}
                className="waves-effect waves-light btn green darken-4"
              >
                Afișare rezultat
              </Link>
            )}
          </div>
        </div>
      </div>
    )
  })

  return (
    <MainLayout>
      <div className="container">
        <CardPanel className="card-panel grey-text text-darken-4">
          <section>
            <h5>
              Ultimele evaluări pentru organizația: {state.organisationName} #{state.organisationId}
            </h5>
          </section>
          <section style={{ margin: '30px 0 0 0' }}>
            <div className="collection">{items}</div>
          </section>
        </CardPanel>
      </div>
    </MainLayout>
  )
}
