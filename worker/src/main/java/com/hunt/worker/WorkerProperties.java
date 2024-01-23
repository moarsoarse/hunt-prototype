package com.hunt.worker;

import lombok.Getter;
import lombok.Setter;
import org.camunda.bpm.client.spring.impl.client.ClientConfiguration;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.util.Map;


@Getter
@Setter
@ConfigurationProperties(prefix = "hunt.worker")
public class WorkerProperties extends ClientConfiguration implements InitializingBean {

    @NestedConfigurationProperty
    protected BasicAuthProperties basicAuth;

    @NestedConfigurationProperty
    protected Map<String, EntityProperties> entities;


    @Override
    public void afterPropertiesSet() throws Exception {
        entities.forEach((k, v) ->
                v.getSubscriptions().forEach((w, s) ->
                    s.setTopicName(workerId.toLowerCase() + "." + w.toLowerCase() + "." + k.toLowerCase())
                )
        );
        LOG
    }

}

