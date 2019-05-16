package com.example.kubernetes.gateway.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

import com.example.kubernetes.gateway.domain.FreightCustomerType


interface FreightCustomerRepository extends JpaRepository<FreightCustomerType, Long>{

	@Query("select fc from FreightCustomerType fc join fetch fc.cost c where c.productWeight.minWeight>=:id and c.productWeight.maxWeight<=:id and fc.organization.id=:orgId and c.deliveryTime=:deliveryTime")
	List<FreightCustomerType> getDeliveryCost(@Param('id') Long productWeight,@Param('orgId') Long orgId,@Param('deliveryTime') String deliveryTime)
}
