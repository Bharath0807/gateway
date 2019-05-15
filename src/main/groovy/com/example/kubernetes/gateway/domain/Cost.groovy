package com.example.kubernetes.gateway.domain

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue;
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name="COST")
class Cost {

	@Id
	@Column(name="COST_PK")
	@GeneratedValue
	Long id
	
	@Column(name="DELIVERY_TIME")
	String deliveryTime
	
	@Column(name="DELIVERY_COST")
	Long deliveryCost
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="FREIGHT_FK")
	FreightCustomerType freightCustomer
	
	@OneToMany(mappedBy="cost")
	List<Shipment> shipment
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="PRODUCT_WEIGHT_FK")
	ProductWeight productWeight
}

