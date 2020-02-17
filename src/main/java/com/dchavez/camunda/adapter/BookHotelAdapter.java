package com.dchavez.camunda.adapter;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class BookHotelAdapter implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		System.out.println("book hotel car for '" + execution.getVariable("name") + "'");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Termino book hotel");

	}

}
