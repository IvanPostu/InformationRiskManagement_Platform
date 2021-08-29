/* eslint-disable @typescript-eslint/ban-ts-comment */
import { render } from 'react-dom';
import 'normalize.css';
import './styles/global.scss';
import { createElement } from 'react';
import { App } from './App';
// import createHashHistory from 'history/createHashHistory';

// const hashHistory = createHashHistory();

const renderApp = () => {
    // const browserHistory = createBrowserHistory();
    // const routeStore = new RouterStore();
    // const history = syncHistoryWithStore(browserHistory, routeStore);
    const rootNode = document.getElementById('root');

    render(createElement(App, {}, null), rootNode);
};

// const rootNode = document.getElementById('root');

// render(createElement(App, {}, null), rootNode);
renderApp();

//@ts-ignore
if (module.hot) {
    //@ts-ignore
    module.hot.accept(() => renderApp());
}
