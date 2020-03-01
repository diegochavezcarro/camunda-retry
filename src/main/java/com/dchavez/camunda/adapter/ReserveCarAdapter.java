package com.dchavez.camunda.adapter;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReserveCarAdapter implements JavaDelegate {

	private static final Logger logger = LoggerFactory.getLogger(ReserveCarAdapter.class);

	@Override
	public void execute(DelegateExecution ctx) throws Exception {

		logger.debug("reserve car for '" + ctx.getVariable("name") + "'");
		// if (true) { throw new RuntimeException("Flight booking did not work"); }

	}

}
