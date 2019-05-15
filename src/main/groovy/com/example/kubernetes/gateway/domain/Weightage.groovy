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

	@Column(name="PRODUCT_COST")
	Long productCost

	@Column(name="TIME_FEEDBACK")
	Long timeFeedback

	@Column(name="DELIVERY_FEEDBACK")
	Long deliveryFeedback

	@Column(name="TOTAL_TIME_FEEDBACK")
	Long totalTimeFeedback

	@Column(name="TOTAL_DELIBERY_FEEDBACK")
	Long totalDeliveryFeedback
}
