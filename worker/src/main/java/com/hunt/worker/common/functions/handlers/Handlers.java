package com.hunt.worker.common.functions.handlers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.hunt.worker.common.entity.AbstractEntity;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskService;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;


public abstract class Handlers {
	
	
	public static Before<ExternalTask, Object> getSingleVariableFromTask = task->
			task.getAllVariables().values().toArray()[0];
	private static final Before<Object,AbstractEntity> extractEntityFromVariable = entityMap -> {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return mapper.convertValue(entityMap, AbstractEntity.class);
	};
	public static final Before<ExternalTask, AbstractEntity> EXTRACT_ENTITY =
			task -> getSingleVariableFromTask.andThen(extractEntityFromVariable).apply(task);
	public static Function<ExternalTask, String> EXTRACT_STRING =
			task -> (String) getSingleVariableFromTask.apply(task);
	
	public static CompleteTask completeWithEntity = (externalTask, externalTaskService, entity) -> {
		TreeMap<String, Object> response = new TreeMap<String, Object>();
		response.put("Entity", entity);
		externalTaskService.complete(externalTask, response);
	};
	
	public static CompleteTask completeWithEntityList = (externalTask, externalTaskService, entityList) -> {
		List<Object> result = (List<Object>) entityList;
		
		Map<String, Object> response= Maps
				.uniqueIndex(result, entity -> entity.toString());
		externalTaskService.complete(externalTask,response);
	};
	
	
	
	@FunctionalInterface
	public interface CompleteTask {
		void complete(ExternalTask externalTask, ExternalTaskService externalTaskService, Object result);
	}
}
