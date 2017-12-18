import React, { Component } from 'react';

import { Form, FormGroup, FormControl, Col, ControlLabel } from 'react-bootstrap';

class InfoComponent extends Component {

  autoSelect(event) {
    event.target.select();
  }

  render() {
    let info = this.props.info;
    let shortenedUrl = window.location.href + info.alias;

    return (
      <Form horizontal>
        <FormGroup>
          <Col componentClass={ControlLabel} sm={3}>
            Shortened URL:
          </Col>
          <Col sm={9}>
            <FormControl
              type="text"
              value={shortenedUrl}
              edit="false"
              onFocus={this.autoSelect}
            />
          </Col>
        </FormGroup>

        <FormGroup>
          <Col componentClass={ControlLabel} sm={3}>
            Original URL:
          </Col>
          <Col sm={9}>
            <FormControl.Static>
              {info.url}
            </FormControl.Static>
          </Col>
        </FormGroup>

        <FormGroup>
          <Col componentClass={ControlLabel} sm={3}>
            Time taken:
          </Col>
          <Col sm={9}>
            <FormControl.Static>
              {info.statistics.time_taken}
            </FormControl.Static>
          </Col>
        </FormGroup>
      </Form>
    );

  }
}

export default InfoComponent;
