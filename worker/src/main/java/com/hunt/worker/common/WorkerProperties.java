package com.hunt.worker.common;

import lombok.Getter;
import lombok.Setter;
import org.camunda.bpm.client.spring.impl.client.ClientConfiguration;
import org.camunda.bpm.client.spring.impl.subscription.SubscriptionConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
@ConfigurationProperties(prefix = "hunt.worker")
public class WorkerProperties extends ClientConfiguration {

    @NestedConfigurationProperty
    protected BasicAuthProperties basicAuth;


  @NestedConfigurationProperty
  protected Map<String, Map<String, SubscriptionConfiguration>> subscriptions = new HashMap<>();



  public SubscriptionConfiguration findSubscriptionPropsByTopicName(String topic) {
      if(subscriptions.get(topic.split("\\.")[1])!=null &&
              subscriptions.get(topic.split("\\.")[1]).get(topic.split("\\.")[2])!=null) {
          return subscriptions.get(topic.split("\\.")[1]).get(topic.split("\\.")[2]);
      }else return null;
  }

}