package com.example.kubernetes.gateway.domain

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="USER_ACCOUNT")
class UserAccount {

	@Id
	@GeneratedValue
	@Column(name="USER_ACCOUNT_PK")
	Long id

	@OneToOne
	UserType customerType
	
	@OneToMany(mappedBy="userAccount")
	List<Shipment> shipment
}
