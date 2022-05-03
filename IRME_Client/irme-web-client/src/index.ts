import React from 'react'
import { render } from 'react-dom'
import './index.css'
import App from './App'
import reportWebVitals from './reportWebVitals'
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from 'chart.js'

ChartJS.register(ArcElement, Tooltip, Legend)

const rootNode = document.getElementById('root')

render(React.createElement(App, null, null), rootNode)

// <React.StrictMode>
//     <App />
//   </React.StrictMode>,
//   rootNode
// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals()
