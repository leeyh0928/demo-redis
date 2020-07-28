package com.example.redis.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@RequiredArgsConstructor
public class DemoRedisApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoRedisApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
