package com.shengyi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.shengyi.mapper")
public class CarApplication {

    public static void main(String[] args){
        SpringApplication.run(CarApplication.class, args);
        System.out.println("============端口号--8091===========");
    }

}
