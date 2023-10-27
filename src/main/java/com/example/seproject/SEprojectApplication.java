package com.example.seproject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.example.seproject.dao")
@ComponentScan(basePackages = "com.example.seproject.*" )
public class SEprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SEprojectApplication.class, args);

    }

}

