package com.todaysneighbor.auth.controller;

import com.todaysneighbor.auth.jwt.JWTUtil;
import com.todaysneighbor.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Slf4j
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AuthController {

    private final JWTUtil jwtUtil;
    private final UserRepository userRepository;

    @GetMapping("/login")
    public void login(@RequestParam String code) {
        // Oauth 처리 로직
        // JWT 토큰 생성 및 반환
    }

    // 로그아웃
    @GetMapping("/logout")
    public void logout() {
        // 로그아웃 처리 로직
    }

    // 회원 탈퇴
    @PatchMapping
    public void withdraw(@RequestHeader("Authorization") String token) {
        // 회원 탈퇴 처리 로직
    }


    // JWT로 ID 가져오기
    @GetMapping("/id")
    public Long getIdByJWT(@RequestHeader("Authorization") String token) {
        token = token.replace("Bearer ", "");
        return jwtUtil.getUserId(token);
    }

    // JWT로 닉네임 가져오기
    @GetMapping("/nickname")
    public String getNicknameByJWT(@RequestHeader("Authorization") String token) {
        token = token.replace("Bearer ", "");
        return jwtUtil.getNickname(token);
    }

    // JWT로 프로필 가져오기
    @GetMapping("/profile")
    public String getProfileByJWT(@RequestHeader("Authorization") String token) {
        token = token.replace("Bearer ", "");
        return jwtUtil.getProfile(token);
    }


}
