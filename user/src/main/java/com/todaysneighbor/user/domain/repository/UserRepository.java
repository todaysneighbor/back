package com.todaysneighbor.user.domain.repository;


import com.todaysneighbor.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByProviderId(Long providerId);
}
