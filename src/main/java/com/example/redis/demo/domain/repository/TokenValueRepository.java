package com.example.redis.demo.domain.repository;

import com.example.redis.demo.domain.Token;
import com.example.redis.demo.domain.Token2;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class TokenValueRepository {
    private final RedisTemplate<String, byte[]> messagePackRedisValueTemplate;
    private final ObjectMapper messagePackObjectMapper;

    public void createToken(Token token) throws JsonProcessingException {
        messagePackRedisValueTemplate.opsForValue().set("token:" + token.getToken(), messagePackObjectMapper.writeValueAsBytes(token));
    }

    public Token getToken(String token) throws IOException {
        return messagePackObjectMapper.readValue(
                Objects.requireNonNull(messagePackRedisValueTemplate.opsForValue().get("token:" + token)), Token.class);
    }

    public Token2 getToken2(String token) throws IOException {
        return messagePackObjectMapper.readValue(
                Objects.requireNonNull(messagePackRedisValueTemplate.opsForValue().get("token:" + token)), Token2.class);
    }
}
