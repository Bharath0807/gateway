package com.example.kubernetes.gateway.domain

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue;
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany;
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name="FREIGHT_CUSTOMER")
class FreightCustomerType {

	@Id
	@Column(name="FREIGHT_CUSTOMER_PK")
	@GeneratedValue
	Long id
	
	@ManyToOne
	@JoinColumn(name="FREIGHT_FK")
	Freight freight
	
	@ManyToOne
	@JoinColumn(name="CUSTOMER_FK")
	CustomerType customerType
	
	@OneToMany(mappedBy="freightCustomer")
	List<Cost> cost
	
	@ManyToOne
	@JoinColumn(name="ORGANIZATION_FK")
	Organization organization
}
