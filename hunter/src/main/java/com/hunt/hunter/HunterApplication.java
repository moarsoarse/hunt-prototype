package com.hunt.hunter;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class HunterApplication {


    public static void main(String... args) {
        ConfigurableApplicationContext c = SpringApplication.run(HunterApplication.class, args);
    }

}
