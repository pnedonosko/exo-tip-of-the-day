import React, { Component } from "react";
import TipOfTheDay from './TipOfTheDay';
import TipOfTheDayAdd from './TipOfTheDayAdd';
import {connect} from 'react-redux';
import { enableAddMode,dissolution, getRandomTip, changeTip, addTip} from '../bll/tipoftheday-reducer';

//class container witch serves to determine the desired form, as well as transfer the necessary data ti them
class TipOfTheDayContainer extends Component{
  //method which is called when an element is created
  componentDidMount(){
      this.props.getRandomTip();
  }
  render(){
    return(
      <div className={(this.props.tipofthedayPage.dissolution)?"tip__app tip__app-dissolution":" tip__app"} >
        {this.props.tipofthedayPage.hasAdd? 
          <TipOfTheDayAdd 
                dissolution={this.props.dissolution}
                changeTip={this.props.changeTip}
                newTip={this.props.tipofthedayPage.newTip}
                addTip={this.props.addTip}/> : 
          <TipOfTheDay getRandomTip={this.props.getRandomTip} tipofthedayPage={this.props.tipofthedayPage} enableAddMode={this.props.enableAddMode} dissolution={this.props.dissolution}/>}
      </div>
    );
  }
}
let mapStateToProps = (state) =>({
  tipofthedayPage: state.tipofthedayPage
});

//transfer data from global store to the container component
export default connect(mapStateToProps, {enableAddMode, dissolution, getRandomTip, changeTip, addTip})(TipOfTheDayContainer);