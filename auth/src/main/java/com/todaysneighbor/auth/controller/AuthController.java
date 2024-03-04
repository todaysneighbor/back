package com.todaysneighbor.auth.controller;

import com.todaysneighbor.auth.jwt.JWTUtil;
import com.todaysneighbor.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Objects;

@RestController
@RequestMapping("/auth")
@Slf4j
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AuthController {

    private final JWTUtil jwtUtil;

//    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
//    private String kakaoClientId;
//
//    @Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
//    private String kakaoClientSecret;
//
//    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
//    private String kakaoRedirectUri;
//
//
//    @GetMapping("/login")
//    public ResponseEntity<RedirectView> login() {
//        String redirectUrl = "https://kauth.kakao.com/oauth/authorize" +
//                "?client_id=" + kakaoClientId +
//                "&redirect_uri=" + kakaoRedirectUri +
//                "&response_type=code&scope=profile_nickname,profile_image";
//
//        return ResponseEntity.ok(new RedirectView(redirectUrl));
//    }


    // 로그아웃
    @GetMapping("/logout")
    public ResponseEntity<Void> logout() {
        // 로그아웃 처리 로직
        return ResponseEntity.ok().build();
    }

    // 회원 탈퇴
    @PatchMapping
    public ResponseEntity<Void> withdraw(@RequestHeader("Authorization") String token) {
        token = token.replace("Bearer ", "");
        Long userID = jwtUtil.getUserId(token);

        return ResponseEntity.ok().build();
    }


    // JWT로 ID 가져오기
    @GetMapping("/id")
    public ResponseEntity<Long> getIdByJWT(@RequestHeader("Authorization") String token) {
        Long userId = jwtUtil.getUserId(token);
        return ResponseEntity.ok(userId);
    }

    // JWT로 닉네임 가져오기
    @GetMapping("/nickname")
    public ResponseEntity<String> getNicknameByJWT(@RequestHeader("Authorization") String token) {
        String nickname = jwtUtil.getNickname(token);
        return ResponseEntity.ok(nickname);
    }

    // JWT로 프로필 가져오기
    @GetMapping("/profile")
    public ResponseEntity<String> getProfileByJWT(@RequestHeader("Authorization") String token) {
        String profile = jwtUtil.getProfile(token);
        return ResponseEntity.ok(profile);
    }


}
