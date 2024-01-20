package com.hunt.worker.common.impl;

import com.hunt.worker.common.WorkerProperties;
import org.camunda.bpm.client.spring.impl.client.ClientPostProcessor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({WorkerProperties.class})
public class WorkerAutoConfiguration {

    @Bean
    public static WorkerPostProcessor workerPostProcessor() {
        return new WorkerPostProcessor(WorkerSubscription.class);
    }

    @Bean
    public static WorkerPostProcessor workerPostProcessor() {
        return new WorkerPostProcessor(WorkerSubscription.class);
    }

    @Bean
    public static ClientPostProcessor clientPostProcessor() {
        return new ClientPostProcessor(WorkerFactory.class);
    }

}
