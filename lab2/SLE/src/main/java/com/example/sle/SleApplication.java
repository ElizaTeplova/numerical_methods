package com.example.sle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class SleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SleApplication.class, args);
        log.info("http://localhost:1337/swagger-ui/index.html");
    }

}
