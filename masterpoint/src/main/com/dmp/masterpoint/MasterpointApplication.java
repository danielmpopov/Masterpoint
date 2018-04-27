package com.dmp.masterpoint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MasterpointApplication {
    public static void main(String[] args) {
        SpringApplication.run(MasterpointApplication.class, args);

    }
}

