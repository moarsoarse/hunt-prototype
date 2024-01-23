
package com.hunt.worker.impl;

import com.hunt.worker.WorkerProperties;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan
@EnableConfigurationProperties({WorkerProperties.class})
public class WorkerAutoConfiguration {

    @Bean
    public WorkerFactory workerFactory() {
        return new WorkerFactory();
    }


    @Autowired
    @Bean(initMethod = "canon")
    public SubscriptionService generateSubscriptions(WorkerProperties workerProperties, BeanFactory beanFactory){
        return new SubscriptionService(workerProperties);
    }

}

