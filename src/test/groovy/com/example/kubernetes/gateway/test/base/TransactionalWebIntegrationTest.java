package com.example.kubernetes.gateway.test.base;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.example.kubernetes.gateway.GatewayApplication;

@ContextConfiguration(classes = GatewayApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@WebAppConfiguration
public @interface TransactionalWebIntegrationTest {

}

