import {tipApi} from "../dal/api";

//Action recognition constants
const ENABLE_ADD_MODE = 'ENABLE_ADD_MODE';
const DISABLE_ADD_MODE = 'DISABLE_ADD_MODE';
const DISSOLUTION = 'DISSOLUTION';
const SET_TIP = 'SET_TIP';
const CHANGE_TIP = 'CHANGE_TIP';

//initial state for reducer, contains all the properties for this type reducer 
let initialState = {
    hasAdd: false,
    dissolution: false,
    user: "",
    text: "",
    datatime: "",
    newTip: ""
};
let text = "";

//switch actions, and writes new data to state
const tipofthedayReducer = (state = initialState, action) => {
    switch (action.type) {
        case ENABLE_ADD_MODE: {
            return {
                ...state,
                hasAdd: true
            };
        }
        case DISABLE_ADD_MODE: {
            return {
                ...state,
                hasAdd: false,
                newTip: ""
            };
        }
        case DISSOLUTION: {
            return {
                ...state,
                dissolution: true
            };
        }
        case SET_TIP:{
            return {
                ...state,
                user: action.user,
                datatime: action.datatime,
                text: action.text
            };
        }
        case CHANGE_TIP: {
            return{
                ...state,
                newTip: action.newTip
            }
        }

        default:
            return state;
    }
};
export const enableAddMode = () =>{
    return{
        type: ENABLE_ADD_MODE
    }
}
export const disableAddMode = () =>{
    return{
        type: DISABLE_ADD_MODE
    }
}
export const dissolution = () =>{
    return{
        type: DISSOLUTION
    }
}
export const changeTip = (newTip) =>{
    text = newTip
    return{
        type: CHANGE_TIP,
        newTip
    }
}
const setTip = (data) =>{
    return{
        type: SET_TIP,
        user: data.poster,
        datatime: new Date(data.posted),
        text: data.text
    }
}


//get random tip, and if status request okM write new data to state
export const getRandomTip= ()=>(dispatch) =>{
    tipApi.getRandomTip().
    then(response => response.json().then(data => {
        if(response.status == 200)
            dispatch(setTip(data))
      })).catch(err => {
        alert("Failed to request random tip: " + JSON.stringify(err));
      });
        
}
//add new tip to database, and get new random tip
export const addTip = () =>(dispatch) =>{
    tipApi.addTip(text)
        .then(response => response.json().then(data => {
            if(response.status == 200)
                dispatch(setTip(data))
      }))
        .catch(err => {
            alert("Failed to update a tip: " + JSON.stringify(err));
        });
}


export default tipofthedayReducer;
