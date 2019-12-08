import React, { Component } from "react";
import "../css/app.less";
import TipOfTheDayContainer from './components/TipOfTheDayContainer';
import { Provider } from 'react-redux'
import {store} from './bll/store'

class App extends Component{
  render(){
    return(
      <Provider store={store}>
          <TipOfTheDayContainer/>
      </Provider>
    );
  }
}

export default App;