import React, { Component } from 'react';
import ShortenUrlComponent from './ShortenUrlComponent';
import ErrorComponent from './ErrorComponent';

class LayoutComponent extends Component {

  getContent() {
    if ('error' in this.props) {
      return <ErrorComponent error={this.props.error} />;
    } else {
      return <ShortenUrlComponent/>;
    }
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

export default LayoutComponent;
