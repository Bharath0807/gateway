package com.example.kubernetes.gateway.domain

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name="USER_ACCOUNT")
class UserAccount {

	@Id
	@GeneratedValue
	@Column(name="USER_ACCOUNT_PK")
	Long id

	@Column(name="USER_NAME")
	String userName
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CUSTOMER_TYPE_FK")
	CustomerType customerType

	@OneToMany(mappedBy="userAccount")
	List<Shipment> shipment
}
