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

	def weightageForTime= [
		"Tomorrow":50,
		"General":35,
		"Prime":50
	]


	// Has cost feedback and delivery feedback for the given weightage for time
	def weightagePercentage = [50:[25, 25],35:[35, 30]]

	def getSuggestions(data) {
		def user = userAccountRespository.findOne(data.userId.asType(Long))
		// fetching the user contracts
		def cost = costRepository.getCostForUserContract(data.productWeight.asType(Long),user.customerType.typeName)

		//fetching all the contracts
		if(user.customerType.typeName!='General')
		{
			def allCost = costRepository.getShipmentCosts(data.productWeight.asType(Long))
			cost.addAll(allCost);
		}
		def retVal = calculateWeightForCost(cost, data)
		retVal
	}

	def calculateWeightForCost(cost, data) {
		def timeWeightage = weightageForTime.get(data.deliveryTime)
		def weightageForCostAndFeedback = weightagePercentage.get(timeWeightage)
		def retVal = []
		cost.each{
			def weight = it.weightage.timeFeedback*(timeWeightage) + it.weightage.deliveryFeedback*(weightageForCostAndFeedback.get(1)/100) + it.deliveryCost*(weightageForCostAndFeedback.get(0)/100)
			def timeFeeback = it.weightage.timeFeedback/it.weightage.totalTimeFeedback
			def deliveryFeedback = it.weightage.deliveryFeedback/it.weightage.totalDeliveryFeedback
			retVal.add(["orgId":it.freightCustomer.organization.id,"weight":weight,"deliveryCost":it.deliveryCost,"orgName":it.freightCustomer.organization.orgName,"freightName":it.freightCustomer.freight.freightName,"deliveryTime":it.deliveryTime, "timeFeedback":timeFeeback,"deliveryFeedback":deliveryFeedback,"type":it.freightCustomer.customerType.typeName])
		}
		retVal.sort{a, b -> a.deliveryCost <=> b.deliveryCost}
		retVal.get(0).putAll(['isSuggested':true])
		retVal.sort{a, b -> b.weight <=> a.weight}
	}
}
