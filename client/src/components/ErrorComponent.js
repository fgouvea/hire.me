import React, { Component } from 'react';

class ErrorComponent extends Component {

  render() {
    return (
      <p> {this.props.error.err_code}: {this.props.error.description} </p>
    );
  }
  
}

export default ErrorComponent;
