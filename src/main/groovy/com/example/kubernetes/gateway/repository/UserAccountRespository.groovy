package com.example.kubernetes.gateway.repository

import org.springframework.data.jpa.repository.JpaRepository

import com.example.kubernetes.gateway.domain.UserAccount

interface UserAccountRespository extends JpaRepository<UserAccount, Long>{

}
