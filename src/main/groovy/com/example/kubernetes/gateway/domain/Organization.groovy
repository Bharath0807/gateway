package com.example.kubernetes.gateway.domain

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ORGANIZATION")
class Organization {

	@Id
	@GeneratedValue
	@Column(name="ORGANIZATION_PK")
	Long id
	
	@Column(name="ORGANIZATION_NAME")
	String orgName
	
	@OneToMany(mappedBy="organization")
	List<FreightCustomerType> freightCustomerType
	
	@OneToMany(mappedBy="organization")
	List<Shipment> shipment
	
}
