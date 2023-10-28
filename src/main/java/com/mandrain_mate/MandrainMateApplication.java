package com.mandrain_mate;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mandrain_mate.mapper")
public class MandrainMateApplication {

    public static void main(String[] args) {
        SpringApplication.run(MandrainMateApplication.class, args);
    }

}
