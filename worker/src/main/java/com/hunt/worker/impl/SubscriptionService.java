package com.hunt.worker.impl;

import com.hunt.worker.EntityProperties;
import com.hunt.worker.WorkerProperties;
import com.hunt.worker.model.AbstractEntity;
import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.spring.impl.subscription.SubscriptionConfiguration;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component()
public class SubscriptionService {

    protected WorkerProperties workerProperties;

    @Autowired
    private ObjectProvider<ExternalTaskHandler> handlerProvider;

    @Autowired
    protected ExternalTaskClient client;

    @Autowired
    public SubscriptionService(WorkerProperties workerProperties) {
        this.workerProperties = workerProperties;
    }

    public void canon() {
        Map<String, EntityProperties> entities = workerProperties.getEntities();
        if (entities != null) {
            for(String entityName : entities.keySet()) {
                Class<? extends AbstractEntity>  entityType = AbstractEntity.childForName(entityName);
                Map<String, SubscriptionConfiguration> subscriptions = entities.get(entityName).getSubscriptions();
                subscriptions.forEach((workerName, subscription) -> client.subscribe(subscription.getTopicName())
                        .handler(handlerProvider.getObject(workerName,entityType)).open());
            }

        }
    }

}