package com.example.redis.demo.domain.repository;

import com.example.redis.demo.DemoRedisApplication;
import com.example.redis.demo.domain.Token;
import com.example.redis.demo.domain.Token2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = DemoRedisApplication.class)
public class TokenValueRepositoryTest {

    @Autowired
    private TokenValueRepository tokenValueRepository;

    @Test
    void createToken() throws IOException {
        Token token = new Token(10, "기저귀");
        tokenValueRepository.createToken(token);

        Token expect = tokenValueRepository.getToken(token.getToken());
        Token2 expect2 = tokenValueRepository.getToken2(token.getToken());

        String result = new String(Base64.getDecoder().decode(expect.getToken()));

    }
}