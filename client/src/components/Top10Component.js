import React, { Component } from 'react';
import axios from 'axios';

import { Table } from 'react-bootstrap';

import ErrorComponent from './ErrorComponent';

class Top10Component extends Component {

  constructor() {
    super();
    this.state = { }
  }
  componentWillMount() {
    axios.get('/top10Urls')
         .then(this.handleAPIResponse.bind(this))
         .catch(this.handleError.bind(this));
  }

  handleAPIResponse(response) {
    this.setState({
      top10: response.data
    });
  }

  handleError(error) {
    const top10_error = {err_code: '000', description: 'Failed to load top 10 urls.'}
    this.setState({
      error: top10_error
    });
  }

  getUrlRow(url, index) {
    const shortenerLink = window.location.href + url.alias;
    
    return (
      <tr key={'url' + index}>
        <th>{index + 1}</th>
        <th><a href={shortenerLink}>{shortenerLink}</a></th>
        <th>{url.url}</th>
        <th>{url.views}</th>
      </tr>
    );
  }

  getTop10Table() {
    return (
      <Table responsive>
        <thead>
          <tr>
            <th>#</th>
            <th>Short</th>
            <th>Full</th>
            <th>Views</th>
          </tr>
          </thead>

          <tbody>
            {
              this.state.top10.map((url, index) => this.getUrlRow(url, index))
            }
          </tbody>
      </Table>
    );
  }

  render() {
    if (this.state.error) {
      return <ErrorComponent error={this.state.error} />
    } else if (this.state.top10) {
      return this.getTop10Table()
    } else {
      return null;
    }
  }
}

export default Top10Component;
