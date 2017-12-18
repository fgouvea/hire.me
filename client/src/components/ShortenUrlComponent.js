import React, { Component } from 'react';
import axios from 'axios';

import { FormControl, FormGroup, ControlLabel, InputGroup, Button } from 'react-bootstrap';

import InfoComponent from './InfoComponent';
import ErrorComponent from './ErrorComponent';
import '../css/ShortenUrl.css'

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

        <FormGroup>
          <ControlLabel>URL:</ControlLabel>
          <FormControl
          type="text"
          name="url"
          placeholder="Ex: http://www.google.com"
          value={this.state.url}
          onChange={this.handleInputChange.bind(this)}
          required="true"
          />
        </FormGroup>

        <FormGroup>
          <ControlLabel>Custom alias:</ControlLabel>
          <InputGroup>
            <InputGroup.Addon>{window.location.href}</InputGroup.Addon>
            <FormControl
            type="text"
            name="alias"
            placeholder="(optional)"
            value={this.state.alias}
            onChange={this.handleInputChange.bind(this)}
            />
          </InputGroup>
        </FormGroup>

        <Button type="submit" bsStyle="primary" className="shorten-button">
          Shorten!
        </Button>

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
