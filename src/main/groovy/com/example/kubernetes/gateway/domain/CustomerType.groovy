package com.example.kubernetes.gateway.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name="CUSTOMER_TYPE")
class CustomerType {

	@Id
	@GeneratedValue
	@Column(name="CUSTOMER_TYPE_PK")
	Long id
	
	@Column(name="CUSTOMER_TYPE_NAME")
	String typeName
	
//	@ManyToMany(mappedBy="customerType")
//	List<Freight> freight
	
	@OneToOne(mappedBy = "customerType")
	UserAccount userAccount
	
	@OneToMany(mappedBy="customerType")
	List<FreightCustomerType> freightCustomer
}
