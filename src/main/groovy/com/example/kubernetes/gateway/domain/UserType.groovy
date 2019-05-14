package com.example.kubernetes.gateway.domain

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="CUSTOMER_TYPE")
class UserType {

	@Id
	@Column(name="CUSTOMER_TYPE_ID")
	Long id
	
	@Column(name="CUSTOMER_TYPE_NAME")
	String typeName
	
//	@ManyToMany(mappedBy="customerType")
//	List<Freight> freight
	
	@OneToMany(mappedBy="customerType")
	List<FreightCustomerType> customer
}
