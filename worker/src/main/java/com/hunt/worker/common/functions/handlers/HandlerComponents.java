package com.hunt.worker.common.functions.handlers;

import com.google.common.collect.Maps;
import org.camunda.bpm.client.task.ExternalTask;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class HandlerComponents {


    public final static After completeWithEntity = (externalTask, externalTaskService, entity) -> {
        TreeMap<String, Object> response = new TreeMap<>();
        response.put("Result", entity);
        externalTaskService.complete(externalTask, response);
    };
    public final static After completeWithEntityList = (externalTask, externalTaskService, entityList) -> {
        List<Object> result = (List<Object>) entityList;

        Map<String, Object> response = Maps
                .uniqueIndex(result, entity -> entity.toString());
        externalTaskService.complete(externalTask, response);
    };
    private final static Before<ExternalTask, Object> getSingleVariableFromTask = task ->
            task.getAllVariables().values().toArray()[0];

    public final static Before<ExternalTask, String> EXTRACT_STRING = externalTask -> (String) getSingleVariableFromTask.apply(externalTask);

    private final static Before<Object, TreeMap<String, Object>> extractEntityFromVariable = entityMap -> (TreeMap<String, Object>) entityMap;
    public final static Before<ExternalTask, TreeMap<String,Object>> EXTRACT_ENTITY = externalTask -> (TreeMap<String, Object>) externalTask.getAllVariables().values().toArray()[0];
            //externalTask -> getSingleVariableFromTask.andThen(extractEntityFromVariable).apply(externalTask);


}
