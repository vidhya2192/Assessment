import React from 'react';
import classes from './Input.css';

const input =(props)=>{
    let inputElement = null;
    let inputClasses = [classes.InputElement];
    if(props.invalid && props.shouldValidate && props.touched){
        inputClasses.push(classes.Invalid)
    }
    switch(props.elementType){
        case('input'):
            inputElement  = <input className={inputClasses.join(' ')} {...props.elementConfig} value={props.value}
                            onChange={props.onChange}  />;
            break;
        case('textarea'):
            inputElement  = <textarea className={inputClasses.join(' ')} {...props.elementConfig} value={props.value}
                            onChange={props.onChange} />;
            break;
        case('select'):
            inputElement = <select className={inputClasses.join(' ')} value={props.value}  onChange={props.onChange} >
                            {props.elementConfig.options.map(optionKey=>(
                                <option  key={optionKey.value} value={optionKey.value}>{optionKey.displayValue}</option>
                            ))}></select> ;
            break;                   
        default:
            inputElement  =  <input className={inputClasses.join(' ')} {...props.elementConfig} value={props.value}  onChange={props.onChange} />;
    }

    return(
        <div className={classes.Input}>
            <label className={classes.Label}>{props.label}</label>
             {inputElement}       
        </div>

    );
}

export default input;