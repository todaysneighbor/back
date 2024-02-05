package com.todaysneighbor.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRegistrationRequest {
    private int price;
    private String filename;
    private String title;
    private String content;
    private String area;
}
