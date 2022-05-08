import React, { useEffect, useState } from 'react'
import { Line } from 'react-chartjs-2'
import { CardPanel } from 'react-materialize'
import { useSelector } from 'react-redux'
import { IEvaluationResult } from '../api/models/IEvaluationResult'
import { SAEvaluationProvider } from '../api/SAEvaluationProvider'
import { MainLayout } from '../layouts/MainLayout'
import { GlobalStateType } from '../store/store'
import base64 from '../utils/base64'

type CategoriesEvaluationsSummaryPageStateType = {
  organisationName: string
  organisationId: number
  categoryResults: Map<number, Array<IEvaluationResult>>
}

function buildOptionsResultData(data: Array<IEvaluationResult>) {
  return {
    responsive: true,
    plugins: {
      legend: {
        position: 'top' as const,
      },
      title: {
        display: true,
        text: data.length > 0 ? data[0].categoryName : '',
      },
    },
  }
}

function buildResultData(data: Array<IEvaluationResult>) {
  return {
    labels: data.map((e) => e.created),
    datasets: [
      {
        label: 'Nivelul curent de securitate',
        data: data.map((d) => d.answerTotalWeight),
        borderColor: 'rgb(255, 99, 132)',
        backgroundColor: 'rgba(255, 99, 132, 0.5)',
      },
      {
        label: 'Nivelul recomandat de securitate',
        data: data.map((d) => d.answerExpectedWeight),
        borderColor: 'rgb(53, 162, 235)',
        backgroundColor: 'rgba(53, 162, 235, 0.5)',
      },
    ],
  }
}

export default function CategoriesEvaluationsSummaryPage() {
  const [state, setState] = useState<CategoriesEvaluationsSummaryPageStateType>({
    organisationId: -1,
    organisationName: '',
    categoryResults: new Map<number, Array<IEvaluationResult>>(),
  })
  const token = useSelector((state: GlobalStateType) => {
    return state.auth.token || ''
  })

  useEffect(() => {
    let isMounted = true
    const paramResolver = new URLSearchParams(location.search)
    const provider = new SAEvaluationProvider()
    const organisationId = Number(paramResolver.get('organisationId'))
    const organisationName = base64.decode(paramResolver.get('organisationName') || '')

    setState((prevState) => ({ ...prevState, organisationId, organisationName }))

    provider.getEvaluationsResults(token, organisationId, 5, null).then((data) => {
      if (!isMounted) return

      if (Array.isArray(data)) {
        const categoryResults: Map<number, Array<IEvaluationResult>> = data.reduce((acc, current) => {
          let items = acc.get(current.categoryId)
          if (items) {
            items.push(current)
          } else {
            items = new Array<IEvaluationResult>()
            items.push(current)
            acc.set(current.categoryId, items)
          }

          return acc
        }, new Map<number, Array<IEvaluationResult>>())

        setState((prevState) => ({ ...prevState, categoryResults: categoryResults }))
      }
    })

    return () => {
      isMounted = false
    }
  }, [])

  const chartElements = Array.from(state.categoryResults).map(([key, value]) => {
    return (
      <div key={key} className="col m6 s12">
        <div style={{ maxWidth: '500px', height: '300px' }}>
          <Line options={buildOptionsResultData(value)} data={buildResultData(value)} />
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
              Rezultate generale pentru organizația: {state.organisationName} #{state.organisationId}
            </h5>
          </section>
          <section style={{ margin: '30px 0 0 0' }}>
            <div className="collection-item   indigo-text text-darken-4">
              <div className="row">{chartElements}</div>
            </div>
          </section>
        </CardPanel>
      </div>
    </MainLayout>
  )
}
