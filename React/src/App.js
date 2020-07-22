import React, { Component } from 'react';
import Registration from './containers/Registration/Registration';
import './App.css';
import Toolbar from './components/Toolbar/Toolbar';

class App extends Component {
  render() {
    return (
      <div className="App">
        <Toolbar></Toolbar>
        <Registration></Registration>
      </div>
    );
  }
}

export default App;
