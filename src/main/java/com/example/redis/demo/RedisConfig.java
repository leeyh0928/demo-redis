package com.example.redis.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.msgpack.jackson.dataformat.MessagePackFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, byte[]> messagePackRedisValueTemplate(LettuceConnectionFactory lettuceConnectionFactory) {

        RedisTemplate<String, byte[]> template = new RedisTemplate<>();
        template.setConnectionFactory(lettuceConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setEnableDefaultSerializer(false);

        return template;
    }

    @Bean
    public RedisTemplate<String, byte[]> messagePackRedisHashTemplate(LettuceConnectionFactory lettuceConnectionFactory) {

        RedisTemplate<String, byte[]> template = new RedisTemplate<>();
        template.setConnectionFactory(lettuceConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        //template.setHashValueSerializer(new StringRedisSerializer());
        template.setEnableDefaultSerializer(false);

        return template;
    }

    @Bean("messagePackObjectMapper")
    public ObjectMapper messagePackObjectMapper() {

        return new ObjectMapper(new MessagePackFactory()).registerModule(new JavaTimeModule()).disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }
}
