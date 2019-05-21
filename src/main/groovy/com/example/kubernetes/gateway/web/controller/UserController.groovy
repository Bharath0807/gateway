package com.example.kubernetes.gateway.web.controller

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kubernetes.gateway.service.UserService;

@RestController
@RequestMapping(value="/api/user")
class UserController {

	@Autowired
	UserService userService
	
	@GetMapping
	def getUsers() {
		userService.getAllUsers()
	}
}
