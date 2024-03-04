package com.todaysneighbor.user.dto;

import com.todaysneighbor.user.domain.entity.Review;
import com.todaysneighbor.user.domain.entity.User;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewCreateRequest {
    @NonNull
    private Long productId;
    @NonNull
    private float rating;
    @NonNull
    private String content;

    public Review toEntity(User user) {
        Review review = new Review();
        review.setUser(user);
        review.setProductId(this.productId);
        review.setRating(this.rating);
        review.setContent(this.content);
        return review;
    }
}