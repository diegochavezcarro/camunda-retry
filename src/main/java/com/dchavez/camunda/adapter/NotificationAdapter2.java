package com.dchavez.camunda.adapter;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotificationAdapter2 implements JavaDelegate {

	private static final Logger logger = LoggerFactory.getLogger(NotificationAdapter2.class);

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		logger.debug("notify2 something wrong for '" + execution.getVariable("name") + "'");

	}

}
