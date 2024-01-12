package com.hunt.common.handler.function.converter;

import com.hunt.common.entity.BaseEntity;
import com.hunt.common.handler.function.CreateNewEntryFunction;
import com.hunt.common.handler.function.WorkerFunction;
import lombok.Getter;
import org.camunda.bpm.client.task.ExternalTaskHandler;

import java.util.Map;

@Getter
@SuppressWarnings({"rawtypes","unchecked"})
public final class HandlerFactory  {
    private final WorkerFunction workerFunction;
    private final String topicType;
    private final String entityName="Hunter";


    public HandlerFactory(WorkerFunction workerFunction)  {
        this.workerFunction=workerFunction;
        if (workerFunction instanceof CreateNewEntryFunction)
            this.topicType = Topic.CREATE.topicType;
        else this.topicType="$null$";
    }

    public ExternalTaskHandler getHandler() {


        if (workerFunction instanceof CreateNewEntryFunction) {
            return (externalTask, externalTaskService) -> {
                BaseEntity entity = externalTask.getVariable("entity");
                BaseEntity result = ((CreateNewEntryFunction) workerFunction).createNew(entity);
                externalTaskService.complete(externalTask, Map.of("result", result));
            };
        } else return null;
    }
    private enum Topic{
        CREATE("createNewEntry");
        private final String topicType;

        Topic(String topicType){
            this.topicType=topicType;
        }
    }
}