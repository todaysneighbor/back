package com.example.apigateway.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class EnvelopResponse<T> {

    @Builder.Default
    private int code = 200;

    @Builder.Default
    private String message = "success";

    private T data;

}