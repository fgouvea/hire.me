import React, { Component } from 'react';
import { Alert } from 'react-bootstrap';

class ErrorComponent extends Component {

  fixMessageCase(message) {
    return message.charAt(0).toUpperCase() + message.slice(1).toLowerCase();
  }

  render() {
    return (
      <Alert bsStyle="danger">
        { this.fixMessageCase(this.props.error.description) }
      </Alert>
    );
  }

}

export default ErrorComponent;
