package com.hunt.worker.common.starter.impl;

import com.hunt.worker.common.starter.WorkerProperties;
import org.camunda.bpm.client.spring.impl.client.ClientPostProcessor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
@EnableConfigurationProperties({WorkerProperties.class})
public class WorkerAutoConfiguration {

  @Bean
  @DependsOn({"clientPostProcessor","functionCatalog"})
  public static WorkerPostProcessor workerPostProcessor() {
    return new WorkerPostProcessor(WorkerSubscription.class);
  }

  @Bean
  public static ClientPostProcessor clientPostProcessor() {
    return new ClientPostProcessor(WorkerFactory.class);
  }

}
