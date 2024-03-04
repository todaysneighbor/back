package com.todaysneighbor.user.dto;

import com.todaysneighbor.user.domain.entity.Review;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponse {
    private Long reviewId;
    private Long productId;
    private float rating;
    private String content;
    private LocalDateTime createdAt;

    public static ReviewResponse of(Review review) {
        return ReviewResponse.builder()
                .reviewId(review.getId())
                .productId(review.getProductId())
                .rating(review.getRating())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
