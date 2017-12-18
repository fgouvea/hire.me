import React, { Component } from 'react';
import axios from 'axios';
import LayoutComponent from './LayoutComponent';

class RedirectComponent extends Component {
  constructor() {
    super();
    this.state = {};
  }

  componentWillMount() {
    // Faz requisição a API.
    axios.get('/' + this.props.match.params.alias).then((response) => {
      this.redirect(response.data.url);
    }).catch((error) => {
      this.setState({error: error.response.data});
    });
  }

  redirect(url) {
    window.location.href = url;
  }

  render() {
    if (this.state.error) {
      return <LayoutComponent error={this.state.error}/>
    } else {
      return null;
    }
  }
}

export default RedirectComponent;
