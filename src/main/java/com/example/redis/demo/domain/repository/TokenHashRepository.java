package com.example.redis.demo.domain.repository;

import com.example.redis.demo.domain.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TokenHashRepository {
    private final RedisTemplate<String, Token> redisTemplate;

    public void createToken(Token token) {
        redisTemplate.opsForHash().putIfAbsent("hash:token",  token.getToken(), token);
    }

    public void createToken62(Token token) {
        redisTemplate.opsForHash().putIfAbsent("hash:token",  token.getToken62(), token);
    }

    public Token getToken(String token) {
        HashOperations<String, String, Token> hashOperations = redisTemplate.opsForHash();
        return hashOperations.get("hash:token", token);
    }
}
