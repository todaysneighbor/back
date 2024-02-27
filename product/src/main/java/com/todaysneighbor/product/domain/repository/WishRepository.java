package com.todaysneighbor.product.domain.repository;

import com.todaysneighbor.product.domain.entity.Wish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WishRepository extends JpaRepository<Wish, Long> {
    Optional<List<Wish>> findAllByUserIdAndIsDeletedIsFalse(Long userId);
}
