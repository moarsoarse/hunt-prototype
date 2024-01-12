package com.hunt.common.handler.function;

import com.hunt.common.annotation.TopicType;
import com.hunt.common.entity.BaseEntity;

import java.util.List;

@TopicType("lookUpForEntry")
@FunctionalInterface
public interface LookUpForEntriesFunction<E extends BaseEntity> extends WorkerFunction<E> {

    List<E> search(String search);

}