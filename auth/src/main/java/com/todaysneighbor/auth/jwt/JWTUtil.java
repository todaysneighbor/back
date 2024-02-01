package com.todaysneighbor.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
@Slf4j
public class JWTUtil {
    private Key key;
    @Value("${spring.jwt.token.secret-key}")
    private String secretKey;
    @Value("${spring.jwt.access-token.expire-length}")
    private Long accessExpireTime;
    @Value("${spring.jwt.refresh-token.expire-length}")
    private Long refreshExpireTime;

    public JWTUtil(@Value("${spring.jwt.token.secret-key}") String secretKey) {
        byte[] byteSecretKey = Decoders.BASE64.decode(secretKey);
        key = Keys.hmacShaKeyFor(byteSecretKey);
    }

    //액세스토큰 생성
    public String createAccessToken(Long userId, String nickname, String profile) {
        log.info("[createAccessToken] 토큰 생성 시작");
        Claims claims = Jwts.claims();
        claims.put("userId", userId);
        claims.put("nickname", nickname);
        claims.put("profile", profile);

        Date now = new Date();
        String accessToken = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + accessExpireTime))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();

        log.info("[createAccessToken] 토큰 생성 완료");
        return accessToken;
    }

    //리프레쉬 토큰 생성
    public String createRefreshToken(Long userId) {
        log.info("[createRefreshToken] 토큰 생성 시작");
        Claims claims = Jwts.claims().setSubject(String.valueOf(userId));

        Date now = new Date();
        String refreshToken = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + refreshExpireTime))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();

        log.info("[createRefreshToken] 토큰 생성 완료");
        return refreshToken;
    }

    //userId 추출
    public Long getUserId(String token) {
        String userId = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().get("userId", String.class);
        log.info("userId 추출 완료 : {}", userId);
        return Long.parseLong(userId);
    }

    //Nickname 추출
    public String getNickname(String token) {
        String Nickname = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().get("nickname", String.class);
        log.info("Nickname 추출 완료 : {}", Nickname);
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().get("nickname", String.class);
    }

    //profile 추출
    public String getProfile(String token) {
        String Profile = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().get("profile", String.class);
        log.info("Profile 추출 완료 : {}", Profile);
        return Profile;
    }

    //유효기간 확인
    public Boolean isExpired(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getExpiration().before(new Date());
    }

}
