package com.example.redis.demo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;

@Getter
@NoArgsConstructor
public class Token2 {
    private String token;
    private String token62;
    private long tokenLength;
    private long token62Length;
    private long tokenId;
    private String keyword;
    private LocalDateTime createdAt;
    private LocalDateTime expiredAt;
    private long new1;
    private String new2;
}
