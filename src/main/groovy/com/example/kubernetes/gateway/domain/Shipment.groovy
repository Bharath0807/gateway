package com.example.kubernetes.gateway.domain

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="SHIPMENT")
class Shipment {

	@Id
	@GeneratedValue
	@Column(name="SHIPMENT_PK")
	Long id

	@Column(name="FROM_ADDRESS")
	String fromAddress

	@Column(name="TO_ADDRESS")
	String toAddress

	@ManyToOne
	@JoinColumn(name="USER_ACCOUNT_FK")
	UserAccount userAccount

	@ManyToOne
	@JoinColumn(name="COST_FK")
	Cost cost
}
