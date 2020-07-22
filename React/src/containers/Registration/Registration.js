import React,{Component} from 'react';
import classes from './Registration.css';
import Button from '../../components/UI/Button/Button';
import Input from '../../components/UI/Input/Input';

class Registration extends Component{
    state={
        joinForm:{
        firstname:{
            label:'First Name',
            elementType:'input',
            elementConfig:{
                type:'text',
                placeholder:'FirstName'
            },
            value:'',
            validation:{
                required:true
            },
            valid:false,
            touched:false
        },
        lastname:{
            label:'Last Name',
            elementType:'input',
            elementConfig:{
                type:'text',
                placeholder:'LastName'
            },
            value:'',
            validation:{
                required:true
            },
            valid:false,
            touched:false
        },
        npinumber:{
            label:'NPI Number',
            elementType:'input',
            elementConfig:{
                type:'text',
                placeholder:'NPI Number'
            },
            value:'',
            validation:{
                required:true,
                isNumber:true
            },
            valid:false,
            touched:false   
        },
        email:{
            label:'EMail Address',
            elementType:'input',
            elementConfig:{
                type:'email',
                placeholder:'EMail Address'
            },
            value:'',
            validation:{
                required:true,
                isEmail:true
            },
            valid:false,
            touched:false   
        },

        address:{
            label:'Business Address',
            elementType:'textarea',
            elementConfig:{
                placeholder:'Address'
            },
            value:'',
            validation:{
                required:true
            },
            valid:false,
            touched:false
        },
        phonenumber:{
            label:'Phone Number',
            elementType:'input',
            elementConfig:{
                type:'text',
                placeholder:'Phone Number'
            },
            value:'',
            validation:{
                required:true,
                lengthRequired:true,
                isNumber:true
            },
            valid:false,
            touched:false
        },
    },
    formIsValid:false,
    registered:false   
    }
    onChangeHandler = (event,id)=>{
        const updatedForm ={
            ...this.state.joinForm
        }
        const formElement = {
            ...updatedForm[id]
        }
        formElement.value = event.target.value;
        formElement.valid = this.checkValidity(formElement.value,formElement.validation);
        formElement.touched = true;
        updatedForm[id] = formElement;
        let formIsValid = true;
        for(let formValidator in updatedForm){
            formIsValid = updatedForm[formValidator].valid && formIsValid;
        }

        this.setState({joinForm:updatedForm,formIsValid:formIsValid});

    }
    checkValidity(value,rules){
        let isValid = true;
        let error = '';
        if(!rules){
            return true;//if not validaton present,just return true
        }
        if(rules.required){
            isValid = value.trim() !== '' && isValid;
        }
        if(rules.lengthRequired){
            isValid = value.length === 10 && isValid;
        }
        if (rules.isEmail) {
            const pattern = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
            isValid = pattern.test(value) && isValid;
        }
        if (rules.isNumber) {
            const pattern = /^\d+$/;
            isValid = pattern.test(value) && isValid;
        }
        this.setState({error:error});
        return isValid;
    }

    registrationHandler = (event)=>{
        event.preventDefault();//To prevent re rendering during form submission
        console.log(this.props);
        const formData={};
        for(let formIdentifier in this.state.joinForm){
            formData[formIdentifier] =this.state.joinForm[formIdentifier].value
        }
        const post ={
            formData:formData   
        }
        this.setState({registered:true})
        console.log("Registration Successful",post);
    }
    render(){
        let formArray =  [];
        for(let key in this.state.joinForm){
            formArray.push({
                id:key,
                config:this.state.joinForm[key]
            });
        }
        let form = (
            <form onSubmit={this.registrationHandler}>
            {
                formArray.map(formInput=>
                    (
                    <Input 
                        key={formInput.id}
                        elementType={formInput.config.elementType} 
                        elementConfig={formInput.config.elementConfig}
                        value={formInput.config.value}
                        invalid={!formInput.config.valid}
                        shouldValidate={formInput.config.validation}
                        touched={formInput.config.touched}
                        onChange={(event)=>this.onChangeHandler(event,formInput.id)}/>
                    )
                )}     
            <Button disabled={!this.state.formIsValid} btnType='Success'>JOIN AVAILITY</Button>
            {this.state.registered?<h4 className={classes.Welcome}>Welcome User</h4>:null}
            </form>
        );
        return(
        <div className={classes.Registration}>
            <h1 className={classes.Label}>Availity Registration Form</h1>
            {form}
        </div>);
    }
}

export default Registration;