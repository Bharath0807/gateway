package com.example.kubernetes.gateway.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import com.example.kubernetes.gateway.repository.CostRepository
import com.example.kubernetes.gateway.repository.FreightCustomerRepository
import com.example.kubernetes.gateway.repository.UserAccountRespository

@Service
class ShipmentService {


	@Autowired
	UserAccountRespository userAccountRespository

	@Autowired
	FreightCustomerRepository freightCustomerRepository

	@Autowired
	CostRepository costRepository

	def WeightageForTime= [
		"Tomorrow":50,
		"General":35,
		"Prime":50
	]

	// Has cost feedback and delivery feedback for the given weightage for time
	def weightagePercentage = [50:[25, 25],35:[35, 30]]

	def getSuggestions(data) {
		def user = userAccountRespository.findOne(data.userId.asType(Long))
		def all = costRepository.findAll()
		def cost = costRepository.getCostForUser(data.productWeight.asType(Long),user.customerType.typeName)
		def retVal = calculateWeightForCost(cost, data)
		println "Return value "
	}
	
	def calculateWeightForCost(cost, data){
		def timeWeightage = WeightageForTime.get(data.deliveryBy)
		def weightageForCostAndFeedback = weightagePercentage.get(timeWeightage)
		def retVal = []
		cost.each{
			println "weightage"+it.weightage
			println "it.freightCustomerType"+it.freightCustomer
			def weight = it.weightage.timeFeedback*(timeWeightage) + it.weightage.deliveryFeedback*(weightageForCostAndFeedback.get(1)/100) + it.deliveryCost*(weightageForCostAndFeedback.get(0)/100)
			retVal.add(["orgId":it.freightCustomer.organization.id,"weight":weight,"deliveryCost":it.deliveryCost])
		}
		
		retVal.sort{a, b -> b.deliveryCost <=> a.deliveryCost}
	}

	def calculateWeight(weightage, weightageForTime) {

		def weightageForCostAndFeedback = weightagePercentage.get(weightageForTime)
		def retVal = [:] as LinkedHashMap
		weightage.each{
			def weight = (weightageForCostAndFeedback.get(0)/100)*it.productCost + (weightageForCostAndFeedback.get(1)/100)*it.deliveryFeedback +(1)/100 + (weightageForTime/100)*it.timeFeedback
			retVal.putAll(["orgId":weight.organization.id,"weight":weight])
		}
		getSuggestedOrganizations(retVal)
	}

	def getSuggestedOrganizations(value) {
		def organizations = []
		value.sort{a, b -> a.weight <=> b.weight}
		value.eachWithIndex{i, index->
			if(index<=1)
				organizations.add(i.orgId)
		}
		organizations
	}
}
