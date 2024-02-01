package com.todaysneighbor.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf((csrf) -> csrf.disable());

        http
                .formLogin((login) -> login.disable());

        http
                .httpBasic((basic) -> basic.disable());

        http
                .oauth2Login(Customizer.withDefaults());

        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/login/**", "oauth2/**").permitAll()
                        .anyRequest().authenticated()
                );

        http
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));


        return http.build();
    }
}