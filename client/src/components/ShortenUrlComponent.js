import React, { Component } from 'react';
import axios from 'axios';

import InfoComponent from './InfoComponent';
import ErrorComponent from './ErrorComponent';

class ShortenUrlComponent extends Component {

  constructor() {
    super();
    this.state = { 'url': '',
                   'alias': '',
                   error: null,
                   response: null }
  }

  handleInputChange(event) {
    const target = event.target;

    this.setState({
      [target.name]: target.value
    });
  }

  handleSubmit(event) {
    let query = "url=" + this.state.url;

    if (this.state.alias.trim().length !== 0) {
      query += "&alias=" + this.state.alias;
    }

    axios.post('/create?' + query)
         .then(this.handleSubtmitSuccess.bind(this))
         .catch(this.handleSubmitError.bind(this));

    event.preventDefault();
  }

  handleSubtmitSuccess(response) {
    this.setState({
      response: response.data
    });
  }

  handleSubmitError(error) {
    this.setState({
      error: error.response.data
    });
  }

  getErrorLabel() {
    if (this.state.error) {
      return <ErrorComponent error={this.state.error} />
    } else {
      return null;
    }
  }

  getShortenForm() {
    return (
      <form onSubmit={this.handleSubmit.bind(this)}>
        <label>
          URL:
          <input type="text" name="url" value={this.state.url} onChange={this.handleInputChange.bind(this)}  />
        </label>
        <label>
          Alias:
          <input type="text" name="alias" value={this.state.alias} onChange={this.handleInputChange.bind(this)}  />
        </label>
        <input type="submit" value="Shorten!"/>

        { this.getErrorLabel() }
      </form>
    );
  }

  render() {
    if (!this.state.response) {
      return this.getShortenForm();
    } else {
      return <InfoComponent info={this.state.response}/>
    }
  }
}

export default ShortenUrlComponent;
