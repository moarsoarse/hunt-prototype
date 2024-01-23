package com.hunt.worker.impl.handler.hugger;

import com.hunt.worker.model.AbstractEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;


@Component
public class Complete {


    public After<TreeMap<String, Object>> withEntity() {
        return (externalTask, externalTaskService, entity) -> externalTaskService.complete(externalTask, entity);
    }

    public After<List<AbstractEntity>> withEntityList() {
        return (externalTask, externalTaskService, entityList) -> {
            Map<String, Object> response = entityList.stream()
                    .collect(Collectors.toMap(
                            key -> key.getId().toString(),
                            key -> key
                    ));
            externalTaskService.complete(externalTask, response);
        };
    }

}