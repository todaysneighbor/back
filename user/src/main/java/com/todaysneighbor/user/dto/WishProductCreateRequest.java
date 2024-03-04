package com.todaysneighbor.user.dto;

import com.todaysneighbor.user.domain.entity.User;
import com.todaysneighbor.user.domain.entity.WishProduct;
import lombok.*;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WishProductCreateRequest {
    @NonNull
    private Long productId;

    public WishProduct toEntity(User user) {
        WishProduct wishProduct = new WishProduct();
        wishProduct.setUser(user);
        wishProduct.setProductId(this.productId);
        return wishProduct;
    }
}