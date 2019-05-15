package com.example.kubernetes.gateway.domain

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PRODUCT_WEIGHT")
class ProductWeight {

	@Id
	@Column(name="PRODUCT_WEIGHT_PK")
	Long id

	@Column(name="PRODUCT_MIN_WEIGHT")
	Long minWeight

	@Column(name="PRODUCT_MAX_WEIGHT")
	Long maxWeight
}
