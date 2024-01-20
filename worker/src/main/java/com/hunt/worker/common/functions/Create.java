package com.hunt.worker.common.functions;

import com.google.common.reflect.TypeToken;
import com.hunt.worker.common.model.AbstractEntity;
import com.hunt.worker.common.model.EntityRepository;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.TreeMap;
import java.util.UUID;

import static com.hunt.worker.common.functions.handlers.HandlerComponents.EXTRACT_ENTITY;
import static com.hunt.worker.common.functions.handlers.HandlerComponents.completeWithEntity;

@FunctionalInterface
public interface Create<E extends AbstractEntity> extends Worker<TreeMap<String,Object>, Optional<UUID>> {


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

