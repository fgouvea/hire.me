import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter, Switch, Route } from 'react-router-dom';
import './index.css';
import App from './App';
import RedirectComponent from './components/RedirectComponent'

ReactDOM.render(
  <BrowserRouter>
    <Switch>
      <Route exact path="/" component={App}></Route>
      <Route path="/:alias" component={RedirectComponent}></Route>
    </Switch>
  </BrowserRouter>,
  document.getElementById('root'));
