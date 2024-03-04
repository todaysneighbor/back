package com.todaysneighbor.user.dto;

import lombok.*;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FollowRequest {
    @NonNull
    private Long followerId;
    @NonNull
    private Long followedId;
}