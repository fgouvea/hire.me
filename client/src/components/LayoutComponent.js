import React, { Component } from 'react';

class RedirectComponent extends Component {

  getContent() {
    if ('error' in this.props) {
      return this.getErrorPanel();
    } else {
      return "";
    }
  }

  getErrorPanel() {
    return <p> {this.props.error.err_code}: {this.props.error.description} </p>
  }

  render() {
    return (
      <div>
        <h1>Shortener</h1>
        {
          this.getContent()
        }
      </div>
    );
  }
}

export default RedirectComponent;
