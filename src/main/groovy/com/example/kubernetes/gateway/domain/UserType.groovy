package com.example.kubernetes.gateway.domain

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="CUSTOMER_TYPE")
class UserType {

	@Id
	@GeneratedValue
	@Column(name="CUSTOMER_TYPE_PK")
	Long id
	
	@Column(name="CUSTOMER_TYPE_NAME")
	String typeName
	
//	@ManyToMany(mappedBy="customerType")
//	List<Freight> freight
	
	@OneToMany(mappedBy="userType")
	List<FreightCustomerType> freightCustomer
}
