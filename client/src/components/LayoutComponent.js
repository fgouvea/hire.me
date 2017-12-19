import React, { Component } from 'react';

import { Navbar, Grid, Row, Col, Panel } from 'react-bootstrap';
import '../css/bootstrap-theme.css';

import ShortenUrlComponent from './ShortenUrlComponent';
import ErrorComponent from './ErrorComponent';
import Top10Component from './Top10Component';

class LayoutComponent extends Component {

  getContent() {
    if ('error' in this.props) {
      return <ErrorComponent error={this.props.error} />;
    } else {
      return <ShortenUrlComponent/>;
    }
  }

  getPanelTitle() {
    return 'error' in this.props ? 'Error' : 'Shorten URL'
  }

  render() {
    return (
      <div className="layout">
        <header>
          <Navbar bsStyle="inverse">
            <Navbar.Header>
              <Navbar.Brand>
                <a href="/">Shortener</a>
              </Navbar.Brand>
              <Navbar.Toggle />
            </Navbar.Header>
          </Navbar>
        </header>

        <Grid>
          <Row>
            <Col md={8} mdOffset={2} sm={12}>
            <Panel header={this.getPanelTitle()} >
            {
              this.getContent()
            }
            </Panel>
            </Col>
          </Row>

          <Row>
            <Col md={8} mdOffset={2} sm={12}>
            <Panel header="Most Viewed" >
              <Top10Component />
            </Panel>
            </Col>
          </Row>
        </Grid>
      </div>
    );
  }
}

export default LayoutComponent;
