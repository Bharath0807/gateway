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
			<tr>
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
		 this.state = {productWeight: '',userId:'',fromAddress:'',toAddress:'',deliveryTime:'', response:''};
		 this.updateProductWeight = this.updateProductWeight.bind(this);
		 this.updateFromAddress = this.updateFromAddress.bind(this);
		 this.updateToAddress = this.updateToAddress.bind(this);
		 this.updateUserId = this.updateUserId.bind(this);
		 this.updateDeliveryTime = this.updateDeliveryTime.bind(this);
		 this.getSuggestion = this.getSuggestion.bind(this);
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
		 <form>
		 	<div class="col-sm-6">User Id</div>
			 <input class="form-control"  type = "text" value = {this.state.userId} 
               onChange = {this.updateUserId} />
		 	 <div class="col-sm-6" >Product weight</div>
			 <input type = "text" class="form-control" value = {this.state.productWeight} 
               onChange = {this.updateProductWeight	} />
            <div class="col-sm-6">From Address</div>
			 <input type = "text" class="form-control" value = {this.state.fromAddress} 
               onChange = {this.updateFromAddress	} />
             <div class="col-sm-6">To Address</div>
			 <input type = "text" class="form-control" value = {this.state.toAddress} 
               onChange = {this.updateToAddress} />
	          <div class="col-sm-6">Select the delivery time:</div>
	          <select value={this.state.deliveryTime} onChange={this.updateDeliveryTime}>
	          <option value="Empty"> </option>
	            <option value="Tomorrow">Tomorrow</option>
	            <option value="General">General</option>
	          </select>
     			
             <button type="button" onClick={this.getSuggestion}>Get Suggestions</button>
             {
				this.state.response?<Suggestion data={this.state}/>:null
			 }
         </form>
		)
	}
	
	getSuggestion() {
    	console.log("Shipment Suggestion",this.state);
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
        	console.log("Response is ", result);
        });
    }
}

ReactDOM.render(
	<Shipment />,
	document.getElementById('react')
)

