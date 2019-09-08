package ru.eltex.app.java.lab8;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@EnableAutoConfiguration
public class QueryProcessingApplication {

    public static void main(String[] args) {
        SpringApplication.run(QueryProcessingApplication.class, args);
    }

}