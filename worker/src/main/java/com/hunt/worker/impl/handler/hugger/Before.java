package com.hunt.worker.impl.handler.hugger;

import org.camunda.bpm.client.task.ExternalTask;

import java.util.function.BiFunction;

public interface Before<E> extends BiFunction<ExternalTask, String, E> {
}
