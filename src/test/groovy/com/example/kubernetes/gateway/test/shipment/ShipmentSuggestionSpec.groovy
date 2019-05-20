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
		def data = ["userId":1L,"productWeight":1,"fromAddress":"","toAddress":"","deliveryBy":"Tomorrow"]
		given: ' We have all the data configured for shipment'
		when: ' The user is selecting one day delivery'
		def res = this.mockMvc.perform(get("/api/shipment/suggestions").content(JsonOutput.toJson(data)).contentType(MediaType.APPLICATION_JSON))
		def  response = new JsonSlurper().parseText(res.andReturn().response.contentAsString)
		println "Response from the server is"+ response
		
		then: ' The suggestions will be displayed to the user'

		and:' the user is giving two day shipment'

		then:' the suggestions will be displayed accordingly to the user'
	}
}
