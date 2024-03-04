package com.example.apigateway.filter;

import com.example.apigateway.dto.EnvelopResponse;
import com.example.apigateway.dto.FindIdResponse;
import com.example.apigateway.exception.AuthException;
import com.example.apigateway.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {

    private final WebClient.Builder webClientBuilder;

    @Autowired
    public AuthFilter(WebClient.Builder webClientBuilder) {
        super(Config.class);
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> processExchange(exchange, chain)
                .onErrorResume(AuthException.class, e -> handleAuthException(exchange, e));
    }

    private Mono<Void> processExchange(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
        if (token != null && !token.isEmpty()) {
            Mono<EnvelopResponse<FindIdResponse>> memberIdMono = webClientBuilder.build()
                    .get()
                    .uri("")
                    .header("Authorization", token)
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<EnvelopResponse<FindIdResponse>>() {})
                    .onErrorResume(WebClientResponseException.Forbidden.class, e ->
                         Mono.error(new AuthException(ErrorCode.AUTH_INVALID))
                    );

            return memberIdMono.flatMap(response -> {
                Long userId = response.getData().getId();
                ServerHttpRequest mutatedRequest = exchange.getRequest().mutate()
                        .header("userId", Long.toString(userId))
                        .build();
                return chain.filter(exchange.mutate().request(mutatedRequest).build());
            });
        }
        return chain.filter(exchange);
    }

    // AuthException 발생 시 클라이언트에 반환할 응답 생성
    private Mono<Void> handleAuthException(ServerWebExchange exchange, AuthException e) {
        log.info("AuthException 발생...");

        EnvelopResponse<String> errorResponse = EnvelopResponse.<String>builder()
                .code(e.getErrorCode().getStatus())
                .message(e.getMessage())
                .data(e.getErrorCode().getMessage())
                .build();

        exchange.getResponse().setStatusCode(HttpStatus.valueOf(e.getErrorCode().getCode()));

        return exchange.getResponse().writeWith(
                Mono.just(exchange.getResponse().bufferFactory().wrap(errorResponse.toString().getBytes()))
        );
    }

    public interface Config {

    }
}