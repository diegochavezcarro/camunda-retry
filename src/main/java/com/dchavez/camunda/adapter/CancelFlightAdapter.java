package com.dchavez.camunda.adapter;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class CancelFlightAdapter implements JavaDelegate {

  @Override
  public void execute(DelegateExecution execution) throws Exception {

    System.out.println("cancel flight for '" + execution.getVariable("name") + "'");

  }

}
