package com.example.redis.demo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Objects;

@Profile("embedded")
@Slf4j
@Configuration
@RequiredArgsConstructor
public class EmbeddedRedisConfig {
    private final Environment environment;
    private RedisServer redisServer;

    @PostConstruct
    public void startRedis() {
        int port = Integer.parseInt(environment.getProperty(
                "spring.redis.port", "6379"));
        log.info(">>> start local redis server, port:" + port);

        redisServer = new RedisServer(port);
        redisServer.start();
    }

    @PreDestroy
    public void stopRedis() {
        log.info("<<< stop local redis server");
        if(Objects.nonNull(redisServer)){
            redisServer.stop();
        }
    }
}
