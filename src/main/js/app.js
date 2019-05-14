'use strict';

// tag::vars[]

import React from 'react';
import  ReactDOM  from 'react-dom';
import client  from './client';

//const React = require('react');
//const ReactDOM = require('react-dom');
//const client = require('./client');
// const Navigation = require('react-navigation');

// end::vars[]

// tag::app[]

class DetailsScreen extends React.Component {
  render() {
    return (
     	<h1>Navigation</h1>
    );
  }
}

class App extends React.Component {

	constructor(props) {
		super(props);
		this.state = {employees: []};
	}

	componentDidMount() {
		client({method: 'GET', path: '/api/employees'}).done(response => {
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
						<th>First Name</th>
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
			</tr>
		)
	}
}
// end::employee[]

// tag::render[]
ReactDOM.render(
	<App />,
	document.getElementById('react')
)
// end::render[]

