package com.dchavez.camunda.adapter;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class CancelCarAdapter implements JavaDelegate {

  @Override
  public void execute(DelegateExecution execution) throws Exception {

     System.out.println("cancel car for '" + execution.getVariable("name") + "'");
    
  }

}
