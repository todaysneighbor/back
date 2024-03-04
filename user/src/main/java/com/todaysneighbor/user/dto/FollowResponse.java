package com.todaysneighbor.user.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FollowResponse {
    private Long followId;
    private Long followerId;
    private Long followedId;
    private LocalDateTime createdAt;
}