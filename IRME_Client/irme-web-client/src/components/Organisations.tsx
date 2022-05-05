import React, { Component, Fragment } from 'react'
import { Button, CardPanel, Container, Table } from 'react-materialize'
import { connect } from 'react-redux'
import { ErrorResult } from '../api/models/ErrorResult'
import { IOrganisation, OrganisationProvider } from '../api/OrganisationProvider'
import { GlobalStateType } from '../store/store'

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
            <Button
              node="a"
              className="grey darken-2"
              small
              style={{
                marginRight: '5px',
              }}
              waves="light"
            >
              Rezultate evaluări
            </Button>
            <Button
              node="a"
              className="grey darken-2"
              small
              style={{
                marginRight: '5px',
              }}
              waves="light"
            >
              Raport general
            </Button>
            <Button
              node="a"
              className="grey darken-2"
              small
              style={{
                marginRight: '5px',
              }}
              waves="light"
            >
              Info
            </Button>
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
