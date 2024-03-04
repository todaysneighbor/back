package com.example.apigateway.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    AUTH_TIME_OUT(701, "001", "토큰이 만료 되었습니다."),
    AUTH_INVALID(702, "002", "토큰이 변조 되었습니다.");
    private final String code;
    private final String message;
    private final int status;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
}
