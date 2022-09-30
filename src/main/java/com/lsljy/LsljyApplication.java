package com.lsljy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lsljy.mapper")//在这里有这个注释，mapper接口就不用再加了
public class LsljyApplication {

    public static void main(String[] args) {
        SpringApplication.run(LsljyApplication.class, args);
    }

}
