package com.example.kubernetes.gateway.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository;

import com.example.kubernetes.gateway.domain.Cost


@Repository
interface CostRepository extends JpaRepository<Cost, Long>{

	
	@Query(nativeQuery=true,value="select distinct * from cost c join freight_customer f on c.FREIGHT_FK=f.FREIGHT_CUSTOMER_PK join customer_type ct on f.CUSTOMER_FK=ct.CUSTOMER_TYPE_PK join product_weight pw on PRODUCT_WEIGHT_FK=pw.PRODUCT_WEIGHT_PK where ct.CUSTOMER_TYPE_NAME=:typeName and  :productWeight<=pw.PRODUCT_MAX_WEIGHT and :productWeight>=pw.PRODUCT_MIN_WEIGHT")
	List<Cost> getCostForUserContract(@Param('productWeight') Long productWeight,@Param('typeName') userType)
	
	@Query(nativeQuery=true,value="select distinct * from cost c join freight_customer f on c.FREIGHT_FK=f.FREIGHT_CUSTOMER_PK join customer_type ct on f.CUSTOMER_FK=ct.CUSTOMER_TYPE_PK join product_weight pw on PRODUCT_WEIGHT_FK=pw.PRODUCT_WEIGHT_PK join organization o on f.ORGANIZATION_FK=o.organization_pk where :productWeight<=pw.PRODUCT_MAX_WEIGHT and :productWeight>=pw.PRODUCT_MIN_WEIGHT and o.ORGANIZATION_NAME='General Organization' and ct.CUSTOMER_TYPE_NAME='General'")
	List<Cost> getShipmentCosts(@Param('productWeight') Long productWeight)
}
