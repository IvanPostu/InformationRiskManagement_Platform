import React, { Component, Fragment, ReactElement } from 'react'
import { Doughnut } from 'react-chartjs-2'
import { Button, CardPanel, Col, Container, Dropdown, Row } from 'react-materialize'
import { IEvaluationReport } from '../api/models/IEvaluationReport'
import { buildEvaluationReportLink } from '../utils/buildEvaluationReportLink'

type EvaluationReportPropsType = {
  readonly report: IEvaluationReport
  readonly _categoryId: number
  readonly _organisationId: number
  readonly _categoryName: string
  readonly _organisationName: string
  readonly _processId: number
}

function dataSet(maxCategoryWeight: number, totalProcessWeight: number, color: string, title: string) {
  const onePercent = maxCategoryWeight / 100
  const securityLevel = totalProcessWeight / onePercent
  const securityEmptyLevel = (maxCategoryWeight - totalProcessWeight) / onePercent
  return {
    labels: [`${title}: ${securityLevel.toFixed(3)}%`],
    datasets: [
      {
        data: [securityLevel, securityEmptyLevel],
        backgroundColor: [color, '#ecf0f1'],
        borderColor: ['#8e44ad', '#d35400'],
        borderWidth: 1,
      },
    ],
    options: {
      tooltips: {
        enabled: false,
      },
    },
  }
}

export class EvaluationReport extends Component<EvaluationReportPropsType> {
  constructor(props: EvaluationReportPropsType) {
    super(props)
  }

  render(): ReactElement {
    const { report, _categoryName, _categoryId, _organisationId, _organisationName, _processId } = this.props
    const { maxCategoryWeight, totalProcessWeight, expectedProcessWeight } = report

    const expectedWeightIsNotReached = expectedProcessWeight > totalProcessWeight

    return (
      <Fragment>
        <Container>
          <CardPanel className="card-panel grey-text text-darken-4">
            <section style={{ margin: '10px 0 30px 0' }}>
              <h4>Raport de evaluare a riscurilor #{_processId}</h4>
              <div className="modal-footer grey-text text-darken-1">
                Organizație: {_organisationName} #{_organisationId}
              </div>
              <div className="modal-footer grey-text text-darken-1">
                Categorie de evaluare: {_categoryName} #{_categoryId}
              </div>
            </section>

            <section style={{ margin: '10px 0 30px 0' }}>
              <Dropdown
                id="Dropdown_14"
                options={{
                  alignment: 'left',
                  autoTrigger: true,
                  closeOnClick: true,
                  constrainWidth: true,
                  container: null,
                  coverTrigger: true,
                  hover: false,
                  inDuration: 150,
                  outDuration: 250,
                }}
                trigger={
                  <Button
                    node="button"
                    style={{
                      marginRight: '5px',
                    }}
                    className="light-blue accent-4"
                    waves="light"
                  >
                    Descarcare raport
                  </Button>
                }
              >
                <a href={buildEvaluationReportLink(_processId, _organisationName, _categoryName, 'docx')}>
                  Format: .DOCX
                </a>
                <a href={buildEvaluationReportLink(_processId, _organisationName, _categoryName, 'doc')}>
                  Format: .DOC
                </a>
              </Dropdown>
            </section>

            <Row>
              <Col m={6} s={12} style={{ display: 'flex', justifyContent: 'space-around' }}>
                <div style={{ maxWidth: '300px' }}>
                  <Doughnut
                    data={dataSet(maxCategoryWeight, totalProcessWeight, '#3498db', 'Nivel curent de securitate')}
                  />
                </div>
              </Col>
              <Col m={6} s={12} style={{ display: 'flex', justifyContent: 'space-around' }}>
                <div style={{ maxWidth: '300px' }}>
                  <Doughnut
                    data={dataSet(
                      maxCategoryWeight,
                      expectedProcessWeight,
                      '#e67e22',
                      'Nivel recomandat de securitate'
                    )}
                  />
                </div>
              </Col>
            </Row>

            <section>
              {expectedWeightIsNotReached ? (
                <CardPanel className=" red lighten-3">
                  <span className="black-text">
                    Nivelul recomandat de securitate NU a fost atins, rugăm să urmați setul de reguli de mai jos...
                  </span>
                </CardPanel>
              ) : (
                <CardPanel className="green lighten-4">
                  <span className="black-text">Nivelul recomandat de securitate a fost atins cu success!!!</span>
                </CardPanel>
              )}
            </section>

            <section>
              <CardPanel className=" grey lighten-3 black-text">
                {report.items.map((r, index) => {
                  const renderHr = index !== report.items.length - 1
                  const k = `${r.questionId}_${r.answerId}`

                  return (
                    <div key={k} style={{ marginBottom: '10px' }}>
                      <p>Întrebare: {r.question}</p>
                      <p>Răspunsul selectat: {r.answer}</p>
                      <p>
                        Modalități de sporire a nivelului securității: <b>{r.description}</b>
                      </p>
                      {renderHr && <hr />}
                    </div>
                  )
                })}
              </CardPanel>
            </section>
          </CardPanel>
        </Container>
      </Fragment>
    )
  }
}
