package com.example.redis.demo.domain.repository;

import com.example.redis.demo.DemoRedisApplication;
import com.example.redis.demo.domain.Token;
import com.example.redis.demo.domain.Token2;
import io.seruco.encoding.base62.Base62;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = DemoRedisApplication.class)
class TokenHashRepositoryTest {

    @Autowired
    private TokenHashRepository tokenHashRepository;

    @Test
    void createToken() throws IOException {
        Token token = new Token(10, "기\"=-$#@!~$%^&*()_+`|\\저귀");
        tokenHashRepository.createToken(token);

        Token expect = tokenHashRepository.getToken(token.getToken());
        Token2 expect2 = tokenHashRepository.getToken2(token.getToken());

        String result = new String(Base64.getDecoder().decode(expect.getToken()));

        Assert.isTrue(expect.getKeyword().equals("기\"=-$#@!~$%^&*()_+`|\\저귀"));
        Assert.isTrue(expect2.getKeyword().equals("기\"=-$#@!~$%^&*()_+`|\\저귀"));
    }

    @Test
    void createToken62() throws IOException {
        Token token = new Token(10, "기\"=-$#@!~$%^&*()_+`|\\저귀");
        tokenHashRepository.createToken62(token);

        Token expect = tokenHashRepository.getToken(token.getToken62());
        Token2 expect2 = tokenHashRepository.getToken2(token.getToken62());

        String result = new String(Base62.createInstance().decode(expect.getToken62().getBytes()));

        Assert.isTrue(expect.getKeyword().equals("기\"=-$#@!~$%^&*()_+`|\\저귀"));
        Assert.isTrue(expect2.getKeyword().equals("기\"=-$#@!~$%^&*()_+`|\\저귀"));
    }
}