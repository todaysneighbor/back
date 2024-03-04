package com.todaysneighbor.user.dto;

import com.todaysneighbor.user.domain.entity.User;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailResponse {
    private Long userId;
    private String nickname;
    private String filename;
    private float rating;
    private LocalDateTime createdAt;

    public static UserDetailResponse of(User user) {
        return UserDetailResponse.builder()
                .userId(user.getId())
                .nickname(user.getNickname())
                .filename(user.getFilename())
                .rating(user.getRating())
                .createdAt(user.getCreatedAt())
                .build();
    }
}