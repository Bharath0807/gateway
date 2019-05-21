package com.example.kubernetes.gateway.service

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kubernetes.gateway.repository.UserAccountRespository;

@Service
class UserService {

	@Autowired
	UserAccountRespository userAccountRespository

	def getAllUsers() {
		def retVal = []
		userAccountRespository.findAll().each {
			retVal.add([id:it.id,name:it.userName])
		}
		retVal
	}
}
