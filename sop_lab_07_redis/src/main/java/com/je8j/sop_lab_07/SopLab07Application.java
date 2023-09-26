package com.je8j.sop_lab_07;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SopLab07Application {

    public static void main(String[] args) {
        SpringApplication.run(SopLab07Application.class, args);
    }

}
