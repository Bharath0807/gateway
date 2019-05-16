package com.example.kubernetes.gateway.test.base


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

import spock.lang.Specification

class WebAppIntegrationBaseSpecification extends Specification {

	MockMvc mockMvc

	@Autowired
	WebApplicationContext wac
	/**
	 * Sets up the mock web application context.
	 */
	void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build()
	}
}
