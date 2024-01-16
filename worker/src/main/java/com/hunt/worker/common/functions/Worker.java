package com.hunt.worker.common.functions;


import org.camunda.bpm.client.task.ExternalTaskHandler;

import java.util.function.Function;

public interface Worker<T,R> extends Function<T,R> {
    ExternalTaskHandler toHandler();

    default String getEntityName() {
        return null;
    }

}

