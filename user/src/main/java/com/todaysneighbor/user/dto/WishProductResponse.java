package com.todaysneighbor.user.dto;

import com.todaysneighbor.user.domain.entity.WishProduct;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WishProductResponse {
    private Long wishProductId;
    private Long productId;
    private LocalDateTime createdAt;

    public static WishProductResponse of(WishProduct wishProduct) {
        return WishProductResponse.builder()
                .wishProductId(wishProduct.getId())
                .productId(wishProduct.getProductId())
                .createdAt(wishProduct.getCreatedAt())
                .build();
    }
}