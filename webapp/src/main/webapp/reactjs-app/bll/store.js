import {createStore, combineReducers, applyMiddleware} from "redux";
import tipofthedayReducer from "./tipoftheday-reducer";
import thunkMiddleware from  "redux-thunk";

//Global project store, combine witch small reducer
let redusers = combineReducers({
    tipofthedayPage : tipofthedayReducer
}); 

export let store = createStore(redusers, applyMiddleware(thunkMiddleware));


