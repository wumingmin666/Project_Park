package com.tjnu.project_park;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@MapperScan("com.tjnu.project_park.mapper")
public class ProjectParkApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectParkApplication.class, args);
    }

}
