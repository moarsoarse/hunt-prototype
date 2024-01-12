package com.hunt.common.handler.function;

import com.hunt.common.entity.BaseEntity;




@FunctionalInterface
public interface CreateNewEntryFunction<E extends BaseEntity> extends WorkerFunction<E> {
    E createNew(E entity);

}
