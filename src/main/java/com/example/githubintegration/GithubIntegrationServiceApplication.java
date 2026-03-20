package com.example.githubintegration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
@EnableCaching
public class GithubIntegrationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GithubIntegrationServiceApplication.class, args);
    }

}
