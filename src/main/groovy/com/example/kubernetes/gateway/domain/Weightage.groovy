package com.example.kubernetes.gateway.domain

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="WEIGHTAGE")
class Weightage {

	@Id
	@Column(name="WEIGHTAGE_PK")
	Long id
	
	@Column()
	Long productCost
}
