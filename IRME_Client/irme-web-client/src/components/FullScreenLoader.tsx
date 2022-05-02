import React, { Fragment } from 'react'
import styled from 'styled-components'

const Overlay = styled.div`
  height: 100%;
  width: 100%;
  position: fixed;
  top: 0;
  left: 0;
  background-color: rgba(0, 0, 0, 0.35);
  overflow: hidden;
  display: flex;
  justify-content: space-between;
`
interface IFullScreenLoaderProps {
  zIndex?: number
}

export function FullScreenLoader(props: IFullScreenLoaderProps) {
  const defaultZIndex = props.zIndex || 5

  return (
    <Fragment>
      <Overlay>
        <div
          style={{
            position: 'relative',
            left: '50%',
            top: '50%',
            width: '70px',
            height: '70px',
            transform: 'translate(-50%, -50%)',
            zIndex: defaultZIndex,
          }}
        >
          <div className="preloader-wrapper big active">
            <div className="spinner-layer" style={{ borderColor: '#34e7e4' }}>
              <div className="circle-clipper left">
                <div style={{ borderWidth: '7px' }} className="circle"></div>
              </div>
              <div className="gap-patch">
                <div style={{ borderWidth: '7px' }} className="circle"></div>
              </div>
              <div className="circle-clipper right">
                <div style={{ borderWidth: '7px' }} className="circle"></div>
              </div>
            </div>
          </div>
        </div>
      </Overlay>
    </Fragment>
  )
}
