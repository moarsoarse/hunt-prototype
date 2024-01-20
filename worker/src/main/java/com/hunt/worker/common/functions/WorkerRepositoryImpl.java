/*
package com.hunt.worker.common.functions;

import com.hunt.worker.common.model.AbstractEntity;
import com.hunt.worker.common.model.EntityMapper;
import jakarta.persistence.EntityManager;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

import static com.hunt.worker.common.functions.Func.endEntity;
import static com.hunt.worker.common.functions.Func.getEntityVar;


//@Configuration
//@Lazy
//@Scope("prototype")
public class WorkerRepositoryImpl<E extends AbstractEntity> extends SimpleJpaRepository<E, UUID> {

    Class<E> entityClass;

    Function<Map<String, Object>, E> dtoer;
   //@Autowired
    public WorkerRepositoryImpl(Class<E> entityClass, EntityManager entityManager,EntityMapper mapper) {
        super(entityClass, entityManager);
        this.entityClass = entityClass;
        this.dtoer =  map -> (E) mapper.dtoToEntity(mapper.mapToDto(map));
    }

    public ExternalTaskHandler creator() {
        return (externalTask, externalTaskService) ->
                endEntity.complete(
                        externalTask,
                        externalTaskService,
                        save(dtoer.apply(getEntityVar.apply(externalTask))));

    }
}
*/
