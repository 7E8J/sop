package com.example.lab03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin(origins = "*", allowedHeaders = "*")

@SpringBootApplication
public class SopLab03Application {

    public static void main(String[] args) {
        SpringApplication.run(SopLab03Application.class, args);
    }

}
