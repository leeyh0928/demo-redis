package com.example.redis.demo.domain.repository;

import com.example.redis.demo.domain.Token;
import com.example.redis.demo.domain.Token2;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class TokenHashRepository {
    private final RedisTemplate<String, byte[]> messagePackRedisHashTemplate;
    private final ObjectMapper messagePackObjectMapper;

    public void createToken(Token token) throws JsonProcessingException {
        messagePackRedisHashTemplate.opsForHash().putIfAbsent("hash:token",  token.getToken(), messagePackObjectMapper.writeValueAsBytes(token));
    }

    public void createToken62(Token token) throws JsonProcessingException {
        messagePackRedisHashTemplate.opsForHash().putIfAbsent("hash:token",  token.getToken62(), messagePackObjectMapper.writeValueAsBytes(token));
    }

    private <T> T getToken(String token, Class<T> clazz) throws IOException {
        HashOperations<String, String, byte[]> hashOperations = messagePackRedisHashTemplate.opsForHash();
        return messagePackObjectMapper.readValue(Objects.requireNonNull(hashOperations.get("hash:token", token)), clazz);
    }

    public Token getToken(String token) throws IOException {
        return this.getToken(token, Token.class);
    }

    public Token2 getToken2(String token) throws IOException {
        return this.getToken(token, Token2.class);
    }
}
