package com.lsljy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lsljy.mapper")
public class LsljyApplication {

    public static void main(String[] args) {
        SpringApplication.run(LsljyApplication.class, args);
    }

}
