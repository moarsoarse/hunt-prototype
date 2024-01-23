package com.hunt.worker.impl.util;

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.spring.exception.NotInitializedException;
import org.camunda.bpm.client.spring.exception.NotOpenedException;
import org.camunda.bpm.client.spring.impl.util.LoggerUtil;
import org.springframework.beans.factory.config.BeanDefinition;

import java.util.Map;

public class WorkerLoggerUtil extends LoggerUtil {

    public static final WorkerLoggerUtil WORKER_LOGGER =
            createLogger(WorkerLoggerUtil.class, PROJECT_CODE, PROJECT_LOGGER, "03");

    public void servicePropertiesLoaded(String[] topics) {
        logDebug("001", "Properties are loaded. Found topics:{}", topics);
    }


}
