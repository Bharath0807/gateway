'use strict';

// tag::vars[]

import React from 'react';
import  ReactDOM  from 'react-dom';
import client  from './client';
import Select from 'react-dropdown-select';

const deliveryTime = [{ label: "General", value: "General" },{ label: "Tomorrow", value: "Tomorrow" }];

class Suggestion extends React.Component {

	constructor(props) {
		super(props);
	}
	render() {
		const suggestions = this.props.data.response.map(suggestion =>
			<DisplaySuggestion  data={suggestion}/>
		);
		console.log("Response in suggestion is", this.props.data);
		return (
			<table class="table table-striped">
			<tbody>
					<tr>
						<th>Freight Name</th>
						<th>Delivery Cost</th>
						<th>Organization Name</th>
						<th>Feedback</th>
						<th>Delivery Time</th>
						<th>Suggested</th>
					</tr>
					{suggestions}
			</tbody>
			</table>
		)
	}
}

class DisplaySuggestion extends React.Component{
	render() {
		return (
			<tr className={(this.props.data.isSuggested ? "highlight-row " : "")}>
				<td>{this.props.data.freightName}</td>
				<td>${this.props.data.deliveryCost}</td>
				<td>{this.props.data.orgName}({this.props.data.type})</td>
				<td>{this.props.data.timeFeedback}</td>
				<td>{this.props.data.deliveryTime}</td>
				<td>{this.props.data.isSuggested?<span class="glyphicon glyphicon-ok"></span>:<span class="glyphicon glyphicon-remove"></span>}</td>
			</tr>
		)
	}
}

class Shipment extends React.Component
{
	constructor(props) {
	    super(props);
		this.state = {productWeight: '',userId:'',fromAddress:'',toAddress:'',deliveryTime:'', response:'', user:[]};
		 this.updateProductWeight = this.updateProductWeight.bind(this);
		 this.updateFromAddress = this.updateFromAddress.bind(this);
		 this.updateToAddress = this.updateToAddress.bind(this);
		 this.updateUserId = this.updateUserId.bind(this);
		 this.updateDeliveryTime = this.updateDeliveryTime.bind(this);
		 this.getSuggestion = this.getSuggestion.bind(this);
		 this.getUser();
	}
	
	getUser() {
	fetch('/api/user', {
 			 method: 'GET',
  			headers: {
    			'Accept': 'application/json',
   				 'Content-Type': 'application/json',
  			},
		}).then(res => res.json())
      .then((result) => {
      console.log("Response is ",result);
      		this.setState({user: result});
      		console.log("Response in state is ",this.state);
        });
	}
	
	updateProductWeight(e) {
      this.setState({productWeight: e.target.value});
   	}
   	updateFromAddress(e) {
      this.setState({fromAddress: e.target.value});
   	}
   	
   	updateToAddress(e) {
      this.setState({toAddress: e.target.value});
   	}
   	
   	updateUserId(e) {
      this.setState({userId: e.target.value});
   	}
   	
   	updateDeliveryTime(e) {
   	  this.setState({deliveryTime: e.target.value});
   	}
   	
   
	render() {
		return (
		 <form class="">
		 	<div class="form-section">
		 		<div class="field-rows">
		 			<div class="field-section">
		 				<div class="field-label">User Id</div>
		 				<div class="field-input"> 
		 					<select className="form-control" onChange={this.updateUserId}>
            						<option >Select the user</option>
						            {(this.state.user).map(msgTemplate => (
						            	
						                <option key={msgTemplate.id} value={msgTemplate.id}>
						                    {msgTemplate.name}
						                </option>
						            ))}
					        </select>
               			</div>
		 			</div>
		 			<div class="field-section">
		 				<div class="field-label">Product weight</div>
		 				<div class="field-input">
		 					<input type = "text" class="text-input" value = {this.state.productWeight} onChange = {this.updateProductWeight	} />
		 				</div>
		 			</div>
		 		</div>
		 		<div class="field-rows">
		 			<div class="field-section">
		 				<div class="field-label">From Address</div>
		 				<div class="field-input"> 
		 					<input type = "text" class="text-input" value = {this.state.fromAddress} onChange = {this.updateFromAddress	} />
               			</div>
		 			</div>
		 			<div class="field-section">
		 				<div class="field-label">To Address</div>
		 				<div class="field-input">
		 					 <input type = "text" class="text-input" value = {this.state.toAddress} onChange = {this.updateToAddress} />
		 				</div>
		 			</div>
		 		</div>
		 		<div class="field-rows">
		 			<div class="field-section">
		 				<div class="field-label">Select the delivery time:</div>
		 				<div class="field-input"> 
		 					<select class="select-input" value={this.state.deliveryTime} onChange={this.updateDeliveryTime}>
					          <option value="Empty"> </option>
					            <option value="Tomorrow">Tomorrow</option>
					            <option value="General">General</option>
					         </select>
               			</div>
		 			</div>
		 		</div>
		 		<div class="result-button field-rows">
		 			<button type="button" onClick={this.getSuggestion}>Get Suggestions</button>
		 		</div>
		 	</div>
             {
				this.state.response?<Suggestion data={this.state}/>:null
			 }
         </form>
		)
	}
	
	getSuggestion() {
    	fetch('/api/shipment/suggestions', {
 			 method: 'PUT',
  			headers: {
    			'Accept': 'application/json',
   				 'Content-Type': 'application/json',
  			},
 			 body: JSON.stringify(this.state)
		}).then(res => res.json())
      .then((result) => {
      		this.setState({response:result});
        });
    }
}

ReactDOM.render(
	<Shipment />,
	document.getElementById('react')
)

