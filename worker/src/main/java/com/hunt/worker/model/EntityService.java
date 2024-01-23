package com.hunt.worker.model;

import com.google.common.reflect.TypeToken;

import java.util.List;
import java.util.TreeMap;


public interface EntityService<E extends AbstractEntity> {

    TreeMap<String, Object> create(TreeMap<String, Object> entity);

    List<TreeMap<String, Object>> search(String search);
    default Class<E> getEntityType() {
        TypeToken<E> type = new TypeToken<>(getClass()) {
        };
        return (Class) type.getType();
    }
}