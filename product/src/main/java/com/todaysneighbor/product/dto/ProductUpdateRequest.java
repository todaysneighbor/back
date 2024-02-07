package com.todaysneighbor.product.dto;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdateRequest {
    @Nullable
    private Integer price;
    @Nullable
    private String filename;
    @Nullable
    private String title;
    @Nullable
    private String content;
    @Nullable
    private String area;
}
