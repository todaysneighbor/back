package com.todaysneighbor.user.domain.repository;

import com.todaysneighbor.user.domain.entity.WishProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishProductRepository extends JpaRepository<WishProduct, Long> {
    List<WishProduct> findByUserIdAndIsDeletedFalse(Long userId);
}
