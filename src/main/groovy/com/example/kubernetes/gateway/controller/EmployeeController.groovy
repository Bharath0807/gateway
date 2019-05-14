package com.example.kubernetes.gateway.controller

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class EmployeeController {

	@RequestMapping(value='/api/employees')
	def getEmployee() {
		[
			["firstName":"test","lastName":"last","description":"Description"],
			["firstName":"user1","lastName":"last1","description":"Description1"]
		]
	}

	@RequestMapping(value = "/index")
	public String index() {
		return "index";
	}
}
