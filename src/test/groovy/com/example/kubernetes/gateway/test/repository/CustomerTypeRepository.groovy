package com.example.kubernetes.gateway.test.repository

import org.springframework.data.jpa.repository.JpaRepository

import com.example.kubernetes.gateway.domain.CustomerType

interface CustomerTypeRepository extends JpaRepository<CustomerType, Long>{

}
