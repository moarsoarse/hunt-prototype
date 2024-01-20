package com.hunt.worker.common.functions.handlers;

import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskService;

@FunctionalInterface
public interface After {
    void complete(ExternalTask externalTask, ExternalTaskService externalTaskService, Object result);
}