package com.hunt.common.handler;

import com.hunt.common.handler.impl.subscription.WorkerPostProcessor;
import org.camunda.bpm.client.spring.boot.starter.ClientProperties;
import org.camunda.bpm.client.spring.boot.starter.impl.PropertiesAwareClientFactory;
import org.camunda.bpm.client.spring.boot.starter.impl.PropertiesAwareSpringTopicSubscription;
import org.camunda.bpm.client.spring.impl.client.ClientPostProcessor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({ClientProperties.class})
public class WorkerPostProcessorConfiguration  {
  @Bean
  public static WorkerPostProcessor workerPostProcessor() {
    return new WorkerPostProcessor(PropertiesAwareSpringTopicSubscription.class);
  }

  @Bean
  public static ClientPostProcessor clientPostProcessor() {
    return new ClientPostProcessor(PropertiesAwareClientFactory.class);
  }


}
