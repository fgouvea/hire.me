import React, { Component } from 'react';

class InfoComponent extends Component {

  autoSelect(event) {
    event.target.select();
  }

  render() {
    let info = this.props.info;
    let shortenedUrl = window.location.href + info.alias;

    return (
      <div>
        <p> Shortened URL: <input value={shortenedUrl} edit="false" onFocus={this.autoSelect} /> </p>
        <p> Original URL: {info.url} </p>
        <p> Time taken: {info.statistics.time_taken}ms </p>
      </div>
    );

  }
}

export default InfoComponent;
