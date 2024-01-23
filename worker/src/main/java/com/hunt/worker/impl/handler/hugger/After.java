package com.hunt.worker.impl.handler.hugger;

import org.apache.logging.log4j.util.TriConsumer;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskService;

public interface After<R> extends TriConsumer<ExternalTask, ExternalTaskService, R> {
}
