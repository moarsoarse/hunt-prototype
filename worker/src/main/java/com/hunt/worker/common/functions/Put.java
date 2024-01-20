package com.hunt.worker.common.functions;

import com.google.common.reflect.TypeToken;
import com.hunt.worker.common.functions.Worker;
import com.hunt.worker.common.model.AbstractEntity;
import org.camunda.bpm.client.task.ExternalTaskHandler;

import java.util.Optional;
import java.util.TreeMap;
import java.util.UUID;
import java.util.function.Supplier;

import static com.hunt.worker.common.functions.handlers.HandlerComponents.EXTRACT_ENTITY;
import static com.hunt.worker.common.functions.handlers.HandlerComponents.completeWithEntity;

@FunctionalInterface
public interface Put extends Supplier<ExternalTaskHandler> {


    default ExternalTaskHandler toHandler() {

        return (externalTask, externalTaskService) ->
                completeWithEntity.complete(
                        externalTask,
                        externalTaskService,
                        compose(EXTRACT_ENTITY).apply(externalTask)
                );
    }

    default String getEntityName() {
        TypeToken<E> type = new TypeToken<>(getClass()) {
        };
        return ((Class) type.getType()).getSimpleName();
    }
}

