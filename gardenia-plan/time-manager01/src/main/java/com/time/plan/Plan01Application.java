package com.time.plan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.time.plan.mapper")
public class Plan01Application {

    public static void main(String[] args) {
        SpringApplication.run(Plan01Application.class, args);
    }

}

