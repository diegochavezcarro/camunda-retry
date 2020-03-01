package com.dchavez.camunda.adapter;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CancelHotelAdapter implements JavaDelegate {

	private static final Logger logger = LoggerFactory.getLogger(CancelHotelAdapter.class);

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		execution.setVariable("name", "viaje2 cancelado");
		logger.debug("cancel hotel for '" + execution.getVariable("name") + "'");

	}

}
