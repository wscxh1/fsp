package com.foodshare;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.foodshare.mapper")
@MapperScan("com.foodshare.config.security")
@SpringBootApplication
public class FoodshareApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodshareApplication.class, args);
    }

}
