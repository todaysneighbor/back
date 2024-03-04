package com.todaysneighbor.user.domain.repository;

import com.todaysneighbor.user.domain.entity.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {
    List<Keyword> findByUserId(Long userId);
}
