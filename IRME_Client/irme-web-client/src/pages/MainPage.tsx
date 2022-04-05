import React, { Fragment } from 'react'
import { Link } from 'react-router-dom'

export function MainPage() {
  return (
      <Fragment>
          <Link to={'/categories'}>categoruies</Link>
      <div>MainPage </div>
      </Fragment>
  )
}
