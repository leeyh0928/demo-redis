package com.example.redis.demo.domain;

import io.seruco.encoding.base62.Base62;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@NoArgsConstructor
public class Token {
    private String token;
    private String token62;
    private long tokenLength;
    private long token62Length;
    private long tokenId;
    private String keyword;
    private LocalDateTime createdAt;
    private LocalDateTime expiredAt;

    public Token(long tokenId, String keyword) {
        this.tokenId = tokenId;
        this.keyword = keyword;
        this.createdAt = LocalDateTime.now();
        this.expiredAt = this.createdAt.plusHours(24);

        long now = this.createdAt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long expire = this.expiredAt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        byte[] keyBytes = String.join(",", String.valueOf(now), String.valueOf(expire))
                .getBytes(StandardCharsets.UTF_8);

        this.token = Base64.getEncoder().encodeToString(keyBytes);

        this.token62 = new String(Base62.createInstance().encode(keyBytes));
        this.tokenLength = token.length();
        this.token62Length = token62.length();
    }

//    public static String sha256(String msg)  throws NoSuchAlgorithmException {
//        MessageDigest md = MessageDigest.getInstance("SHA-256");
//        md.update(msg.getBytes());
//        return Base64.getEncoder().encodeToString(md.digest());
//    }
}
