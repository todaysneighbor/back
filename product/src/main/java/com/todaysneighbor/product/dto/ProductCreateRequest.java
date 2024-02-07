package com.todaysneighbor.product.dto;

import com.todaysneighbor.product.domain.entity.Product;
import jakarta.annotation.Nullable;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateRequest {
    @NonNull
    private int price;
    @Nullable
    private String filename;
    @NonNull
    private String title;
    @NonNull
    private String content;
    @NonNull
    private String area;

    public static Product toEntity(ProductCreateRequest request, Long userId){
        return Product.builder()
                .userId(userId)
                .title(request.getTitle())
                .content(request.getContent())
                .areaId(1L) //임시
                .price(request.getPrice())
                .filename(request.filename)
                .categoryId(1L) //임시
                .build();
    }
}
