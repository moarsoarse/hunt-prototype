package com.hunt.worker.common;

import lombok.Getter;
import lombok.Setter;
import org.camunda.bpm.client.spring.impl.subscription.SubscriptionConfiguration;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


@Getter
@Setter
public class EntityController {
    private String entityTypeName;
    private String entityDataSource;
    @NestedConfigurationProperty
    private Map<String, SubscriptionConfiguration> subscriptions = new HashMap<>();
}
