package com.dchavez.camunda.adapter;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class BookFlightAdapter implements JavaDelegate {

  @Override
  public void execute(DelegateExecution execution) throws Exception {

     System.out.println("book flight crashes for '"+ execution.getVariable("name")+"'");
     
		
		  if (true) { throw new RuntimeException("Flight booking did not work"); }
		 
    
  }



}
