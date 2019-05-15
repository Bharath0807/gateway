package com.example.kubernetes.gateway.domain
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.OneToMany;
import javax.persistence.Table

@Entity
@Table(name="FREIGHT_MANAGEMENT")
class Freight {

	@Column(name="FREIGHT_MANAGEMENT_PK")
	Long id

	@Column(name="FREIGHT_NAME")
	String freightName

//	@ManyToMany(cascade=CascadeType.ALL)
//	@JoinTable(name="FREIGHT_CUSTOMER",joinColumns=[@JoinColumn(name = "FREIGHT_MANAGEMENT_FK")],inverseJoinColumns=[@JoinColumn(name="CUSTOMER_TYPE_ID")])
//	List<CustomerType> customerType
	
	@OneToMany(mappedBy="freight")
	List<FreightCustomerType> freightCustomerType
}
