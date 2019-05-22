package com.example.kubernetes.gateway.test.shipment

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

import javax.transaction.Transactional;

import groovy.json.JsonOutput;
import groovy.json.JsonSlurper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType

import com.example.kubernetes.gateway.repository.UserAccountRespository;
import com.example.kubernetes.gateway.test.base.TransactionalWebIntegrationTest
import com.example.kubernetes.gateway.test.base.WebAppIntegrationBaseSpecification
import com.example.kubernetes.gateway.test.repository.CustomerTypeRepository;

@TransactionalWebIntegrationTest
class ShipmentSuggestionSpec extends WebAppIntegrationBaseSpecification {

	@Autowired
	UserAccountRespository userAccountRespository

	@Autowired
	CustomerTypeRepository customerTypeRepository

	def "Get Suggestion for shipment"() {
		def data = ["userId":1L,"productWeight":2,"fromAddress":"","toAddress":"","deliveryTime":"Tomorrow"]
		given: ' We have all the data configured for shipment'
		when: ' The user is selecting one day delivery and the user is not a prime customer'
		def res = this.mockMvc.perform(put("/api/shipment/suggestions").content(JsonOutput.toJson(data)).contentType(MediaType.APPLICATION_JSON))
		def oneDayResponse = new JsonSlurper().parseText(res.andReturn().response.contentAsString)
		int generalUser=0
		def suggestedAmountGeneralOneday, oneDayWeights=[]

		oneDayResponse.each{
			oneDayWeights.add(it.weight)
			if(it.type == 'General')
				generalUser++;
			if(it.isSuggested)
				suggestedAmountGeneralOneday = it.deliveryCost
		}
		then: ' The suggestions will be displayed to the user for the available general shipment options as the user is not prime customer'
		assert generalUser == oneDayResponse.size()
		assert suggestedAmountGeneralOneday == 25

		and:' the user is giving General delivery date by General'
		def generalDeliveryData = ["userId":1L,"productWeight":1,"fromAddress":"","toAddress":"","deliveryTime":"General"]
		def generalResponse = this.mockMvc.perform(put("/api/shipment/suggestions").content(JsonOutput.toJson(generalDeliveryData)).contentType(MediaType.APPLICATION_JSON))
		then:' the suggestions will be displayed accordingly to the user sorted according to the weight calcaulated in the backend'
		def generalDelivery = new JsonSlurper().parseText(generalResponse.andReturn().response.contentAsString)
		int generalUserGeneralDelivery=0
		def suggestedAmountGeneralDelivery, generalWeights=[]
		generalDelivery.each{
			generalWeights.add(it.weight)
			if(it.type == 'General')
				generalUserGeneralDelivery++;
			if(it.isSuggested)
				suggestedAmountGeneralDelivery = it.deliveryCost
		}
		assert generalWeights != oneDayWeights
		assert  generalUserGeneralDelivery == generalDelivery.size()
		assert suggestedAmountGeneralDelivery == 25
		and:' The user has been changed to the prime customer'
		markUserPrime()
		and:' The same user is trying to ship the product by entering the product details'
		def primeData = ["userId":1L,"productWeight":25,"fromAddress":"","toAddress":"","deliveryTime":"Tomorrow"]

		def primeOnedayResponse= this.mockMvc.perform(put("/api/shipment/suggestions").content(JsonOutput.toJson(data)).contentType(MediaType.APPLICATION_JSON))
		def primeOnedayData = new JsonSlurper().parseText(primeOnedayResponse.andReturn().response.contentAsString)
		int primeGeneralDelivery=0, generalType=0,primeType=0, primeDelivery=0
		def suggestedAmountPrimeDelivery, primeWeights=[]
		primeOnedayData.each{
			primeWeights.add(it.weight)
			it.type == 'General'?primeGeneralDelivery++:primeDelivery++
			it.isSuggested?(suggestedAmountPrimeDelivery = it.deliveryCost):''
		}
		assert primeGeneralDelivery == 2
		assert primeDelivery == 4
		assert suggestedAmountPrimeDelivery == 15
	}

	@Transactional
	void markUserPrime() {
		def primeCustomerType = customerTypeRepository.findOne(2L)
		def user = userAccountRespository.findOne(1L)
		user.customerType = primeCustomerType
		userAccountRespository.save(user)
	}
}
