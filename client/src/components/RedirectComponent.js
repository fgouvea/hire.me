import React, { Component } from 'react';
import axios from 'axios';
import LayoutComponent from './LayoutComponent';

class RedirectComponent extends Component {
  constructor() {
    super();
    this.state = {error: null};
  }

  componentWillMount() {
    axios.get('/' + this.props.match.params.alias).then((response) => {
      if ('url' in response.data) {
        // Redireciona para a URL original encurtada
        window.location.href = response.data.url;
      }
    }).catch((error) => {
        this.setState({error: error.response.data});
    });
  }

  render() {
    if (this.state.error === null) {
      return "";
    } else {
      return (
        <LayoutComponent error={this.state.error}/>
      )
    }
  }
}

export default RedirectComponent;
