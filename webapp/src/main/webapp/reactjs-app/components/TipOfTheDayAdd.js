import React from 'react';


const TipOfTheDayAdd = (props)=>{
    const newTipRef = React.createRef();
    //functions that are called during some actions and call passed callbacks in props
    const disableAddMode = () => {
    	addTip()
        props.disableAddMode()
    }
    const dissolution = () =>{
        props.dissolution()
    }
    const changeTip = () =>{
        props.changeTip(newTipRef.current.value)
    }
    const addTip = () =>{
    	props.addTip(newTipRef.current.value);

    }
    return (
        <div className="tip__container tip__container-add">
            <div className={"tip__container-about tip__smallElement"}>?</div>
            <div className={"tip__biggerElement tip__container-text_add"}>
                <input  type="text" 
                        ref={newTipRef} 
                        onChange={changeTip} 
                        id={"tip__container-text_add-input"} 
                        placeholder="Tooltip text..."
                        value={props.newTip}></input>
            </div>
            <div className={"tip__mediumElement tip__container-save " } onClick={disableAddMode}>Save</div>
            <div className={"tip__container-exit tip__smallElement"} onClick={dissolution}>&times;</div>
        </div>
    );
};
export default TipOfTheDayAdd;