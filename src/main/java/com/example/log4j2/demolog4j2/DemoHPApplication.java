package com.example.log4j2.demolog4j2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DemoHPApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoHPApplication.class, args);
    }
}
