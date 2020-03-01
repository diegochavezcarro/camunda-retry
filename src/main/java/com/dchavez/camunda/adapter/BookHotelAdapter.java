package com.dchavez.camunda.adapter;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.policy.TimeoutRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

public class BookHotelAdapter implements JavaDelegate {
	private static final Logger logger = LoggerFactory.getLogger(BookHotelAdapter.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	

	/*
	 * public BookHotelAdapter(RestTemplate restTemplate) { super();
	 * this.restTemplate = restTemplate; }
	 */


	@Override
	public void execute(DelegateExecution execution) throws Exception {

		logger.debug("book hotel car for '" + execution.getVariable("name") + "'");
		/*
		 * try { Thread.sleep(1000); } catch (InterruptedException e) {
		 * e.printStackTrace(); }
		 */
		
		
		Map<Class<? extends Throwable>, Boolean> r = new HashMap<>();
		//r.put(RetryException.class, true);
		r.put(ResourceAccessException.class, true);

		SimpleRetryPolicy simplePolicy = new SimpleRetryPolicy(5, r);
		// Set the max retry attempts
		simplePolicy.setMaxAttempts(5);

		// Política BackOff
		FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();

		// 5000ms -> 5s
		backOffPolicy.setBackOffPeriod(5000);
		TimeoutRetryPolicy timeoutpolicy = new TimeoutRetryPolicy();
		timeoutpolicy.setTimeout(15000);
		RetryTemplate retryTemplate = new RetryTemplate();
		retryTemplate.setRetryPolicy(simplePolicy);
		retryTemplate.setBackOffPolicy(backOffPolicy);
		//retryTemplate.setRetryPolicy(timeoutpolicy);

		String result = retryTemplate.execute(arg0 -> {
			logger.debug("hace un retry");
			 
			ResponseEntity<String> restExchange = restTemplate.exchange("http://localhost:8082/products/", HttpMethod.GET,
					null, String.class);

			logger.debug("resultado de productos: " + restExchange.getBody());
			return restExchange.getBody();
			
			
		}, arg0 -> {
			logger.debug("hace un recovery");
			throw new Exception("no fue posible");
		});

		
		
		/*
		 * ResponseEntity<String> restExchange = restTemplate.exchange(
		 * "http://localhost:8082/products/", HttpMethod.GET, null, String.class);
		 * 
		 * logger.debug("resultado de productos: " + restExchange.getBody());
		 */		
		logger.debug("Termino book hotel");

	}

}
