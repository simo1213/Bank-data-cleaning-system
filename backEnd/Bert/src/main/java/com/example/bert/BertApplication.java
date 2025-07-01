package com.example.bert;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.bert.mapper")
public class BertApplication {

    public static void main(String[] args) {
        SpringApplication.run(BertApplication.class, args);
    }

}
