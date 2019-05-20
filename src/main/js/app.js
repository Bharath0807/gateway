'use strict';

// tag::vars[]

import React from 'react';
import  ReactDOM  from 'react-dom';
import client  from './client';
import Select from 'react-select';

const deliveryTime = [{ label: "General", value: 1 },{ label: "Tomorrow", value: 2 }];
class Shipment extends React.Component
{


	constructor(props) {
		super(props);
		 this.state = {productWeight: '',userId:'',fromAddress:'',toAddress:'',deliveryTime:''};
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
   	  this.setState({deliveryTime: e.label});
   	}
   
	render() {
		return (
		 <form>
		 	<div>User Id</div>
			 <input type = "text" value = {this.state.userId} 
               onChange = {this.updateUserId} />
		 	 <div>Product weight</div>
			 <input type = "text" value = {this.state.productWeight} 
               onChange = {this.updateProductWeight	} />
            <div>From Address</div>
			 <input type = "text" value = {this.state.fromAddress} 
               onChange = {this.updateFromAddress	} />
             <div>To Address</div>
			 <input type = "text" value = {this.state.toAddress} 
               onChange = {this.updateToAddress} />
             <Select options={ deliveryTime } onChange={this.updateDeliveryTime}/>
             <button type="button" onClick={this.getSuggestion}>Get Suggestions</button>
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
		});
    }
}

class App extends React.Component {

	constructor(props) {
		super(props);
		this.state = {employees: []};
	}

	componentDidMount() {
		client({method: 'GET', path: '/api/fdfsdf'}).done(response => {
			console.log(response);
			this.setState({employees: response.entity});
		});
	}

	render() {
		return (
			<EmployeeList employees={this.state.employees}/>
		)
	}
}
// end::app[]
	
class EmployeeList extends React.Component{

	constructor(props)
	{	
		super(props);
		this.handleClick = this.handleClick.bind(this);
	}
	
	handleClick() {
		console.log("Button Clicked");
	}
	  
	render() {
		const employees = this.props.employees.map(employee =>
			<Employee  employee={employee}/>
		);
		return (
			<table>
				<tbody>
					<tr>
						<th>Test Name</th>
						<th>Last Name</th>
						<th>Description</th>
					</tr>
					{employees}
				</tbody>
				<button onClick={this.handleClick}>Click</button>
			</table>
			
		)
	}
}


class First extends React.Component{
	render() {
		return (
			<h1>Hello World</h1>
		)
	}
}


class Employee extends React.Component{
	render() {
		return (
			<tr>
				<td>{this.props.employee.firstName}</td>
				<td>{this.props.employee.lastName}</td>
				<td>{this.props.employee.description}</td>
				<td>{this.props.employee.description}</td>
			</tr>
		)
	}
}
// end::employee[]

// tag::render[]
ReactDOM.render(
	<Shipment />,
	document.getElementById('react')
)
// end::render[]

