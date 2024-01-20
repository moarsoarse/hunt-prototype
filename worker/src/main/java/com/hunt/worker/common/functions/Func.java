package com.hunt.worker.common.functions;

import com.hunt.worker.common.functions.handlers.After;
import com.hunt.worker.common.functions.handlers.Before;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskService;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class Func {
    static After endEntity = (externalTask, externalTaskService, entity) -> {
        Map<String, Object> response = Collections.singletonMap("Result", entity);
        externalTaskService.complete(externalTask, response);
    };

    public static void endEntities(ExternalTask externalTask, ExternalTaskService externalTaskService, List<?> entityList) {
        Map<String, Object> response = new HashMap<>();

        entityList.forEach(entity -> response.put(entity.toString(), entity));

        externalTaskService.complete(externalTask, response);
    }

    static Before<ExternalTask, Object> getRawVar = task ->
            task.getAllVariables().values().iterator().next();

    static Before<ExternalTask, String> getStrVar = externalTask -> (String) getRawVar.apply(externalTask);

    private static Before<Object, Map<String, Object>> anyToEntity = entityMap -> ((Map<String, Object>) entityMap);
    static Before<ExternalTask, Map<String, Object>> getEntityVar = externalTask -> anyToEntity.apply(getRawVar.apply(externalTask));
}
