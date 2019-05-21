package com.example.kubernetes.gateway.test.shipment

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import groovy.json.JsonOutput;
import groovy.json.JsonSlurper;

import org.springframework.http.MediaType

import com.example.kubernetes.gateway.test.base.TransactionalWebIntegrationTest
import com.example.kubernetes.gateway.test.base.WebAppIntegrationBaseSpecification

@TransactionalWebIntegrationTest
class ShipmentSuggestionSpec extends WebAppIntegrationBaseSpecification {

	def "Get Suggestion for shipment"() {
		def data = ["userId":1L,"productWeight":2,"fromAddress":"","toAddress":"","deliveryTime":"Tomorrow"]
		given: ' We have all the data configured for shipment'
		when: ' The user is selecting one day delivery'
		def res = this.mockMvc.perform(put("/api/shipment/suggestions").content(JsonOutput.toJson(data)).contentType(MediaType.APPLICATION_JSON))
		def oneDayResponse = new JsonSlurper().parseText(res.andReturn().response.contentAsString)
		
		then: ' The suggestions will be displayed to the user'
		assert oneDayResponse != null
		and:' the user is giving General delivery date by'
		def generalDeliveryData = ["userId":1L,"productWeight":1,"fromAddress":"","toAddress":"","deliveryTime":"General"]
		def generalResponse = this.mockMvc.perform(put("/api/shipment/suggestions").content(JsonOutput.toJson(generalDeliveryData)).contentType(MediaType.APPLICATION_JSON))
		then:' the suggestions will be displayed accordingly to the user'
		assert generalResponse != null
	}
}
