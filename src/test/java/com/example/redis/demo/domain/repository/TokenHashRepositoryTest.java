package com.example.redis.demo.domain.repository;

import com.example.redis.demo.DemoRedisApplication;
import com.example.redis.demo.domain.Token;
import io.seruco.encoding.base62.Base62;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest(classes = DemoRedisApplication.class)
class TokenHashRepositoryTest {

    @Autowired
    private TokenHashRepository tokenHashRepository;

    @Test
    void createToken() {
        Token token = new Token(10, "기\"=-$#@!~$%^&*()_+`|\\저귀");
        tokenHashRepository.createToken(token);

        Token expect = tokenHashRepository.getToken(token.getToken());

        Assert.isTrue(expect.getKeyword().equals("기\"=-$#@!~$%^&*()_+`|\\저귀"));
    }

    @Test
    void createToken62() {
        Token token = new Token(10, "기\"=-$#@!~$%^&*()_+`|\\저귀");
        tokenHashRepository.createToken62(token);

        Token expect = tokenHashRepository.getToken(token.getToken62());

        String result = new String(Base62.createInstance().decode(expect.getToken62().getBytes()));

        Assert.isTrue(expect.getKeyword().equals("기\"=-$#@!~$%^&*()_+`|\\저귀"));
    }
}