package com.hunt.worker.common.impl.util;

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.spring.exception.NotInitializedException;
import org.camunda.bpm.client.spring.exception.NotOpenedException;
import org.camunda.bpm.client.spring.impl.util.LoggerUtil;
import org.springframework.beans.factory.config.BeanDefinition;

import java.util.Map;

public class WorkerLoggerUtil extends LoggerUtil {

    public static final WorkerLoggerUtil WORKER_LOGGER =
            createLogger(WorkerLoggerUtil.class, PROJECT_CODE, PROJECT_LOGGER, "03");

    public void workerBeansFound(Class<?> workerFunctionClass,
                                 Map<String, ?> beanNamesForType) {
        logDebug("001", "Handler beans found for {}: {}",
                workerFunctionClass, beanNamesForType);
    }

    public void beanRegistered(String subscriptionBeanName, String handlerBeanName) {
        logDebug("002", "Subscription bean '{}' registered for handler bean '{}'",
                subscriptionBeanName, handlerBeanName);
    }

    public void notFound(BeanDefinition beanDefinition) {
        logInfo("003", "Subscription not found for handler bean {}", beanDefinition);
    }

    public void found(ExternalTaskSubscription annotation, BeanDefinition beanDefinition) {
        logDebug("004", "Subscription found {} for handler bean {}", annotation,
                beanDefinition);
    }

    public void initialized(String topicName) {
        logDebug("005", "Subscription for topic name {} initialized", topicName);
    }

    public void opened(String topicName) {
        logDebug("006", "Subscription for topic name {} opened", topicName);
    }

    public void closed(String topicName) {
        logDebug("007", "Subscription for topic name {} closed", topicName);
    }

    public NotInitializedException notInitializedException(String topicName) {
        return new NotInitializedException(exceptionMessage(
                "008", "Subscription with topic name '{}' has yet not  been initialized", topicName));
    }

    public NotOpenedException notOpenedException(String topicName) {
        return new NotOpenedException(exceptionMessage(
                "009", "Subscription with topic name '{}' has yet not  been opened", topicName));
    }

}
