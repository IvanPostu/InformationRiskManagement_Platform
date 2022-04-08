import React from 'react'
import './App.css';
import { BrowserRouter, HashRouter, Redirect, Route, Switch } from 'react-router-dom';
import { MainPage } from './pages/MainPage';
import { CategoriesPage } from './pages/CategoriesPage';

function App() {
  return (
    <HashRouter>
     <Switch>
                <Route
                exact
                path='/'
                component={MainPage}
                />
    <Route
    exact
      path='/categories'
      component={CategoriesPage}
    />
    <Route
      path="*"
      component={MainPage}
    />
     </Switch>
    </HashRouter>
  );
}

export default App;
