import React, { Component, Fragment } from 'react'
import { CardPanel, Container, Table } from 'react-materialize'
import { connect } from 'react-redux'
import { Link } from 'react-router-dom'
import { ErrorResult } from '../api/models/ErrorResult'
import { IOrganisation, OrganisationProvider } from '../api/OrganisationProvider'
import { GlobalStateType } from '../store/store'
import base64 from '../utils/base64'

function mapStateToProps(state: GlobalStateType) {
  const { token } = state.auth

  return {
    token: token || '',
  }
}

type OrganisationsComponentPropsType = ReturnType<typeof mapStateToProps>
type OrganisationsComponentStateType = {
  organisations: Array<IOrganisation>
}

class OrganisationsComponent extends Component<OrganisationsComponentPropsType, OrganisationsComponentStateType> {
  private _isMounted = false
  private _organisationprovider: OrganisationProvider = new OrganisationProvider()

  constructor(props: OrganisationsComponentPropsType) {
    super(props)

    this.state = {
      organisations: [],
    }
  }

  componentDidMount() {
    this._isMounted = true

    this._organisationprovider.userOrganisations(this.props.token).then((d) => {
      if (!this._isMounted) return

      if (d !== null && !(d instanceof ErrorResult)) {
        this.setState({
          organisations: d,
        })
      }
    })
  }

  componentWillUnmount() {
    this._isMounted = false
  }

  render() {
    const { organisations } = this.state
    const tableElements = organisations.map((o) => {
      return (
        <tr key={o.id}>
          <td>{o.id}</td>
          <td>{o.name}</td>
          <td>{o.created}</td>
          <td>
            <Link
              to={'/'}
              className="waves-effect waves-light btn-small grey darken-2"
              style={{
                marginRight: '5px',
              }}
            >
              Rezultate evaluări
            </Link>
            {/* <a href="http://localhost:8080/api/securityAssessment/download?processId=2016&organisationName=QjJCIFNvbHV0aW9ucw==&categoryName=UHJvdGVjyJtpZSBwZXJpbWV0cmFsxIM=&docType=docx">
              Raport
            </a> */}

            <Link
              to={`/currentOrganisationEvaluations/?organisationId=${o.id}&organisationName=${base64.encode(o.name)}`}
              className="waves-effect waves-light btn-small grey darken-2"
              style={{
                marginRight: '5px',
              }}
            >
              Evaluări curente
            </Link>
          </td>
        </tr>
      )
    })

    return (
      <Fragment>
        <Container>
          <CardPanel className="card-panel grey-text text-darken-4">
            <Table>
              <thead>
                <tr>
                  <th data-field="id">Id:</th>
                  <th data-field="name">Denumire:</th>
                  <th data-field="created">Data creării:</th>
                  <th data-field="actions">Acțiuni:</th>
                </tr>
              </thead>
              <tbody>{tableElements}</tbody>
            </Table>
          </CardPanel>
        </Container>
      </Fragment>
    )
  }
}

export const Organisations = connect(mapStateToProps)(OrganisationsComponent)
