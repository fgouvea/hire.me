import React, { Component } from 'react';

import { Navbar, Nav, NavItem, NavDropdown, MenuItem, Grid, Col, Panel } from 'react-bootstrap';
import '../css/bootstrap-theme.css';

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
          <Col md={8} mdOffset={2} sm={12}>
            <Panel header={this.getPanelTitle()} >
            {
              this.getContent()
            }
            </Panel>
          </Col>
        </Grid>
      </div>
    );
  }
}

export default LayoutComponent;
