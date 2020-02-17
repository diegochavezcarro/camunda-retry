package com.dchavez.camunda;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableProcessApplication
public class CamundaRetryApplication {

	public static void main(String[] args) {
		SpringApplication.run(CamundaRetryApplication.class, args);
	}

}
