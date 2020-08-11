package com.example.redis.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableConfigurationProperties(RedisProperties.class)
@RequiredArgsConstructor
public class RedisConfig {
    private final RedisProperties redisProperties;

//    @Bean
//    public ClientOptions clientOptions(){
//        return ClientOptions.builder()
//                .disconnectedBehavior(ClientOptions.DisconnectedBehavior.REJECT_COMMANDS)
//                .autoReconnect(true)
//                .build();
//    }
//
//    @Bean
//    public LettucePoolingClientConfiguration lettucePoolConfig(ClientOptions options, ClientResources dcr) {
//        return LettucePoolingClientConfiguration.builder()
//                .poolConfig(new GenericObjectPoolConfig())
//                .clientOptions(options)
//                .clientResources(dcr)
//                .build();
//    }
//
//    @Bean
//    public RedisClusterConfiguration redisClusterConfiguration() {
//        RedisClusterConfiguration redisConfiguration = new RedisClusterConfiguration(
//                redisProperties.getCluster().getNodes());
//        redisConfiguration.setPassword(RedisPassword.of(redisProperties.getPassword()));
//
//        return redisConfiguration;
//    }
//
//    @Bean
//    public LettuceConnectionFactory redisConnectionFactory(LettucePoolingClientConfiguration lettucePoolConfig) {
//        RedisClusterConfiguration redisConfiguration = new RedisClusterConfiguration(redisProperties.getCluster().getNodes());
//        redisConfiguration.setPassword(RedisPassword.of(redisProperties.getPassword()));
//        return new LettuceConnectionFactory(redisConfiguration,lettucePoolConfig);
//    }

    @Bean
    public RedisTemplate<?, ?> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        RedisTemplate<String, ?> template = new RedisTemplate<>();
        template.setConnectionFactory(lettuceConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

        return template;
    }
}
