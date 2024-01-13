package com.hunt.common.starter;

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
@ConfigurationProperties(prefix = "camunda.bpm.client")
public class WorkerProperties extends ClientConfiguration {

  @NestedConfigurationProperty
  protected Map<String, SubscriptionConfiguration> subscriptions = new HashMap<>();

  @NestedConfigurationProperty
  protected BasicAuthProperties basicAuth;

  public SubscriptionConfiguration findSubscriptionPropsByTopicName(String topic) {
    return subscriptions.get(topic);
  }

}