package com.hunt.worker.impl.handler;

import com.hunt.worker.impl.handler.hugger.Complete;
import com.hunt.worker.impl.handler.hugger.Extract;
import com.hunt.worker.model.AbstractEntity;
import com.hunt.worker.model.EntityService;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.List;


@Configuration
public class HandlerFactory{

    @Autowired
    protected List<EntityService> entityServices;

    @Autowired
    private ObjectProvider<EntityService> provider;

    @Autowired
    protected Extract extract;
    @Autowired
    protected Complete complete;

    public ExternalTaskHandler createHandler(EntityService  entityService) {
        return (externalTask, externalTaskService) -> {
            complete.withEntity().accept(externalTask, externalTaskService,
                    entityService.create(extract.entity().apply(externalTask, "entity")));
        };
    }

    public ExternalTaskHandler searchHandler(EntityService  entityService) {
        return (externalTask, externalTaskService) -> {
            complete.withEntityList().accept(externalTask, externalTaskService,
                    entityService.search(extract.string().apply(externalTask, "entity")));
        };
    }


    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public ExternalTaskHandler getHandler(String worker,Class<? extends AbstractEntity> entityClass){
        EntityService entityService = getEntityService(entityClass);
        switch (worker.toLowerCase()) {
            case "create":
                return createHandler(entityService);
            case "search":
                return searchHandler(entityService);
        }
        return null;
    }

    public EntityService getEntityService(Class<? extends AbstractEntity> entityClass){

        return entityServices.stream().filter(s -> s.getEntityType().equals(entityClass)).findFirst().orElse(null);
    }
}
