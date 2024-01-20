package com.hunt.worker.common.functions;


import com.hunt.worker.common.model.EntityRepository;
import org.camunda.bpm.client.task.ExternalTaskHandler;

import java.util.function.Function;

public interface Worker<T, R> extends Function<T, R> {
    final EntityRepository entityRepository;

    ExternalTaskHandler toHandler();

    default String getEntityName() {
        return null;
    }



}

