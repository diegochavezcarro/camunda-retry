package com.dchavez.camunda.adapter;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BookFlightAdapter implements JavaDelegate {

	private static final Logger logger = LoggerFactory.getLogger(BookFlightAdapter.class);

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		// System.out.println("book flight crashes for '"+
		// execution.getVariable("name")+"'");
		logger.debug("book flight crashes for '" + execution.getVariable("name") + "'");

		if (true) {
			throw new RuntimeException("Flight booking did not work");
		}

	}

}
