package com.hunt.camunda.worker;

import java.util.Collections;
import java.util.logging.Logger;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HunterWorkers  {
  private final static Logger LOGGER = Logger.getLogger(HunterWorkers.class.getName());


  @Bean
  @ExternalTaskSubscription("lookUp")
  public ExternalTaskHandler lookUpHandler() {
    return (externalTask, externalTaskService) -> {
      String someProcessVariable = externalTask.getVariable("someProcessVariable");

      boolean isNumber = false;
      try {
        Long.parseLong(someProcessVariable);
        isNumber = true;
      } catch (NumberFormatException e) {
        LOGGER.info(someProcessVariable + " is not numeric");
      }

      LOGGER.info("Returning validate=" + isNumber);

      // Complete the task
      externalTaskService.complete(externalTask, Collections.singletonMap("isNumber", isNumber));
    };
  }

  @Bean
  @ExternalTaskSubscription("create")
  public ExternalTaskHandler createHandler() {
    return (externalTask, externalTaskService) -> {
      String someProcessVariable = externalTask.getVariable("someProcessVariable");
      someProcessVariable=Long.parseLong(someProcessVariable)*2 +"";
      externalTaskService.complete(externalTask, Collections.singletonMap("someProcessVariable", someProcessVariable));
    };
  }
}
