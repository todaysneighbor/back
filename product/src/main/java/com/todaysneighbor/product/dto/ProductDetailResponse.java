package com.todaysneighbor.product.dto;

import com.todaysneighbor.product.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailResponse {
    private long productId;
    private int price;
    private String filename;
    private int viewCount;
    private int wishCount;
    private String title;
    private String content;
    private String area;
    private LocalDateTime createdAt;
    private LocalDateTime refreshedAt;
    private int state;

    public static ProductDetailResponse of(Product product) {
        return ProductDetailResponse.builder()
                .productId(product.getId())
                .price(product.getPrice())
                .filename(product.getFilename())
                .viewCount(product.getViewCount())
                .wishCount(product.getWishCount())
                .title(product.getTitle())
                .content(product.getContent())
                .createdAt(product.getCreatedAt())
                .refreshedAt(product.getRefreshedAt())
                .area("임시 주소")
                .build();
    }
}
