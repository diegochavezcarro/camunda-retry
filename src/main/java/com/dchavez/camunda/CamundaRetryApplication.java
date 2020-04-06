package com.dchavez.camunda;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.dchavez.camunda.adapter.BookHotelAdapter;
import com.dchavez.camunda.adapter.NotificationAdapter;
import com.dchavez.camunda.adapter.NotificationAdapter2;

@SpringBootApplication
@EnableProcessApplication
public class CamundaRetryApplication {
	
	@Bean
	public RestTemplate restTemplate() {
		RestTemplate template = new RestTemplate();
		return template;
	}
	
	@Bean
	public BookHotelAdapter bookHotel() {
		return new BookHotelAdapter();
	}	
	
	@Bean
	public NotificationAdapter notification() {
		return new NotificationAdapter();
	}	
	
	@Bean
	public NotificationAdapter2 notification2() {
		return new NotificationAdapter2();
	}	

	public static void main(String[] args) {
		SpringApplication.run(CamundaRetryApplication.class, args);
	}

}
