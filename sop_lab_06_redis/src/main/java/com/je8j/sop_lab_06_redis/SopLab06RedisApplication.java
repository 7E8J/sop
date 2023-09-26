package com.je8j.sop_lab_06_redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SopLab06RedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SopLab06RedisApplication.class, args);
    }

}
