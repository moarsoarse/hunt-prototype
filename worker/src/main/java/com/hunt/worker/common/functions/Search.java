package com.hunt.worker.common.functions;

import com.google.common.reflect.TypeToken;
import com.hunt.worker.common.model.AbstractEntity;
import org.camunda.bpm.client.task.ExternalTaskHandler;

import java.util.List;

import static com.hunt.worker.common.functions.handlers.HandlerComponents.EXTRACT_STRING;
import static com.hunt.worker.common.functions.handlers.HandlerComponents.completeWithEntityList;

@FunctionalInterface
public interface Search<E extends AbstractEntity> extends Worker<String, List<Object>> {
    default ExternalTaskHandler toHandler() {
        return (externalTask, externalTaskService) -> completeWithEntityList.complete(externalTask, externalTaskService, compose(EXTRACT_STRING).apply(externalTask));
    }

    default String getEntityName() {
        TypeToken<E> type = new TypeToken<>(getClass()) {
        };
        return ((Class) type.getType()).getSimpleName();
    }
}
