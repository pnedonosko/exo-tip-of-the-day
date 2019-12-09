import React from 'react';
import ReactTooltip from 'react-tooltip'


const TipOfTheDay = (props)=>{
    const enableAddMode = () => {
      props.enableAddMode();
    }
    const dissolution = () =>{
      props.dissolution();
    }
    const getRandomTip = () =>{
      props.getRandomTip();
    }
    return (
      <div className="tip__container">
        <div className={"tip__container-about tip__smallElement"}>?</div>
        <div className={"tip__container-text tip__biggerElement"} 
              data-place="bottom"
              data-effect="solid"
              data-multiline="true"
              data-class="tooltip"
              data-event='click focus'
              data-tip={props.tipofthedayPage.text} 
              data-for='tip-tooltip'><span>{props.tipofthedayPage.text}</span>
        </div>
        <ReactTooltip globalEventOff='click' id='tip-tooltip'>{}</ReactTooltip>
        {props.tipofthedayPage.user == "system"?<></>:
            <div className={"tip__container-user tip__bigElement"}>via {props.tipofthedayPage.user}</div>}
        {props.tipofthedayPage.user == "system"?<></>:
            <div className={"tip__container-datetime tip__bigElement"}>{props.tipofthedayPage.datatime}</div>}
        <div className={"tip__container-next tip__mediumElement"} onClick={getRandomTip}>></div>
        <div className={"tip__container-add tip__mediumElement"} onClick={enableAddMode}>+</div>
        <div className={"tip__container-exit tip__smallElement"} onClick={dissolution}>&times;</div>
      </div>
    );
};
export default TipOfTheDay;