package com.todaysneighbor.user.dto;

import com.todaysneighbor.user.domain.entity.Keyword;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeywordResponse {
    private Long keywordId;
    private String keyword;
    private LocalDateTime createdAt;

    public static KeywordResponse of(Keyword keyword) {
        return KeywordResponse.builder()
                .keywordId(keyword.getId())
                .keyword(keyword.getKeyword())
                .createdAt(keyword.getCreatedAt())
                .build();
    }
}