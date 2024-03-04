package com.todaysneighbor.auth.oauth2;

import com.todaysneighbor.auth.jwt.JWTUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JWTUtil jwtUtil;

    public CustomSuccessHandler(JWTUtil jwtUtil) {

        this.jwtUtil = jwtUtil;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // OAuth2User로부터 사용자 정보 추출
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = oAuth2User.getAttributes();
        Long providerId = (Long) attributes.get("id");
        String nickname = (String) ((Map<String, Object>) attributes.get("properties")).get("nickname");
        String profileImageUrl = (String) ((Map<String, Object>) attributes.get("properties")).get("profile_image");

        // JWT 토큰 생성
        String accessToken = jwtUtil.createAccessToken(providerId, nickname, profileImageUrl);

        // 생성된 액세스 토큰을 쿠키에 추가
        Cookie cookie = createCookie("Authorization", accessToken);
        response.addCookie(cookie);

        // 사용자를 리디렉션할 URL 설정
        response.sendRedirect("http://localhost:3000/");
    }


    private Cookie createCookie(String key, String value) {

        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(60*60*60);
        //cookie.setSecure(true); //https에서만 쿠키사용
        cookie.setPath("/");
        cookie.setHttpOnly(true);

        return cookie;
    }
}