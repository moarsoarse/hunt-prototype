package com.hunt.worker.common.functions;

import com.google.common.reflect.TypeToken;
import com.hunt.worker.common.entity.AbstractEntity;
import org.camunda.bpm.client.task.ExternalTaskHandler;

import java.util.Optional;

import static com.hunt.worker.common.functions.handlers.Handlers.*;

@FunctionalInterface
public interface Create<E extends AbstractEntity> extends Worker<E, Optional<E>>{
	
	@Override
	default ExternalTaskHandler toHandler(){
		return (externalTask, externalTaskService) -> completeWithEntity.complete(externalTask, externalTaskService, apply((E) EXTRACT_ENTITY.apply(externalTask)));
	}

	default String getEntityName() {
		TypeToken<E> type = new TypeToken<>(getClass()) {
		};
		return ((Class) type.getType()).getSimpleName();
	}
}
