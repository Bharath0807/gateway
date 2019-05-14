package com.example.kubernetes.gateway.domain

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name="COST")
class Cost {

	@Id
	@Column(name="COST_ID")
	Long id
	
	@Column(name="DELIVERY_TIME")
	String deliveryTime
	
	@Column(name="DELIVERY_COST")
	Long deliveryCost
	
	@OneToOne(cascade=CascadeType.ALL)
	FreightCustomerType freightCustomer
	
}
