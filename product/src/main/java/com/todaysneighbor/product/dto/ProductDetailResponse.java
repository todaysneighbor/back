package com.todaysneighbor.product.dto;

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
    private int productPrice;
    private String filename;
    private int viewCount;
    private int wishCount;
    private String title;
    private String content;
    private String area;
    private LocalDateTime createdAt;
    private LocalDateTime refreshedAt;
    private int state;
}
