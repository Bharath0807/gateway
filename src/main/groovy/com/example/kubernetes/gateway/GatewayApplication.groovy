package com.example.kubernetes.gateway

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration;



@SpringBootApplication
@Configuration
@ComponentScan(value="com.example.kubernetes.gateway")
class GatewayApplication {

	static void main(String[] args) {
		SpringApplication.run(GatewayApplication, args)
	}
}
