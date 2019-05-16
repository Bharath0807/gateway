package com.example.kubernetes.gateway.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import com.example.kubernetes.gateway.service.ShipmentService

@RestController
@RequestMapping("/api/shipment")
class ShipmentController {

	@Autowired
	ShipmentService shipmentService
	
	@GetMapping(value="/suggestions")
	def getSuggestions(@RequestBody data) {
		shipmentService.getSuggestions(data)
	}
}
