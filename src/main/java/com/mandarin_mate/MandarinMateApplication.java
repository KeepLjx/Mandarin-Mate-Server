package com.mandarin_mate;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mandarin_mate.mapper")
public class MandarinMateApplication {

    public static void main(String[] args) {
        SpringApplication.run(MandarinMateApplication.class, args);
    }

}
