package com.hunt.camunda.worker;

import com.hunt.common.handler.function.CreateNewEntryFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {


    @Bean
    public CreateNewEntryFunction<Hunter> createNew() {
        return (Hunter h) ->
                 {
                    h.setName("муча");
                    System.out.println("IM HERE!!!");
                    return h;
                };

        };

}


