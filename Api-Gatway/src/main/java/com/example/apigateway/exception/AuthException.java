package com.example.apigateway.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthException extends RuntimeException{

    private final ErrorCode errorCode;

}
