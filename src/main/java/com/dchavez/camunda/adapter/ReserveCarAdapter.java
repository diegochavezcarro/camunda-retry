package com.dchavez.camunda.adapter;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class ReserveCarAdapter implements JavaDelegate {

  @Override
  public void execute(DelegateExecution ctx) throws Exception {

    System.out.println("reserve car for '" + ctx.getVariable("name") + "'");
    //if (true) { throw new RuntimeException("Flight booking did not work"); }

  }

}
