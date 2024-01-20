package com.hunt.worker.common.model;


import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.TreeMap;

@Configuration
public class EntityServiceImpl implements EntityService {

    @Autowired
    protected EntityRepository entityRepository;

    protected @Autowired EntityMapper entityMapper;

    @Bean
    public ExternalTaskHandler create() {
        return (externalTask, externalTaskService) -> {
            AbstractEntity entity  = entityMapper.dtoToEntity((TreeMap<String,Object>) externalTask.getVariable("entity"));
            TreeMap<String,Object> entitySaved = entityMapper.entityToDto((AbstractEntity)entityRepository.save(entity));
            externalTaskService.complete(externalTask, entitySaved);

        };
    }
}


