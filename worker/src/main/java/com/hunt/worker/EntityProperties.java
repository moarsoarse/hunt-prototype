package com.hunt.worker;

import lombok.Getter;
import lombok.Setter;
import org.camunda.bpm.client.spring.impl.subscription.SubscriptionConfiguration;

import java.util.Map;


@Getter
@Setter
public class EntityProperties {

    private String tableName;

    private Map<String, SubscriptionConfiguration> subscriptions;


}
