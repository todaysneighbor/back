package com.todaysneighbor.user.service;

import com.todaysneighbor.user.domain.entity.*;
import com.todaysneighbor.user.domain.repository.*;
import com.todaysneighbor.user.dto.*;
import com.todaysneighbor.user.exception.ErrorCode;
import com.todaysneighbor.user.exception.business.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final WishProductRepository wishProductRepository;
    private final ReviewRepository reviewRepository;
    private final KeywordRepository keywordRepository;
    private final NotificationRepository notificationRepository;
    private final FollowRepository followRepository;

    // 유저 상세 정보 조회
    @Transactional(readOnly = true)
    public UserDetailResponse getUserDetails(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.ENTITY_NOT_FOUND));
        return UserDetailResponse.of(user);
    }

    // 위시리스트에 상품 추가
    @Transactional
    public void addWishProduct(Long userId, WishProductCreateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.ENTITY_NOT_FOUND));
        WishProduct wishProduct = request.toEntity(user);
        wishProductRepository.save(wishProduct);
    }

    // 위시리스트 조회
    @Transactional(readOnly = true)
    public List<WishProductResponse> getWishList(Long userId) {
        List<WishProduct> wishProducts = wishProductRepository.findByUserIdAndIsDeletedFalse(userId);
        return wishProducts.stream().map(WishProductResponse::of).collect(Collectors.toList());
    }

    // 리뷰 작성
    @Transactional
    public void createReview(Long userId, ReviewCreateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.ENTITY_NOT_FOUND));
        Review review = request.toEntity(user);
        reviewRepository.save(review);
    }

    // 사용자별 리뷰 조회
    @Transactional(readOnly = true)
    public List<ReviewResponse> getReviewsByUserId(Long userId) {
        List<Review> reviews = reviewRepository.findByUserId(userId);
        return reviews.stream().map(ReviewResponse::of).collect(Collectors.toList());
    }

    // 키워드 추가
    @Transactional
    public void addKeyword(Long userId, KeywordCreateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.ENTITY_NOT_FOUND));
        Keyword keyword = request.toEntity(user);
        keywordRepository.save(keyword);
    }

    // 사용자별 키워드 조회
    @Transactional(readOnly = true)
    public List<KeywordResponse> getKeywordsByUserId(Long userId) {
        List<Keyword> keywords = keywordRepository.findByUserId(userId);
        return keywords.stream().map(KeywordResponse::of).collect(Collectors.toList());
    }

    // 팔로우 기능
    @Transactional
    public void followUser(FollowRequest request) {
        User follower = userRepository.findById(request.getFollowerId())
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.ENTITY_NOT_FOUND));
        User followed = userRepository.findById(request.getFollowedId())
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.ENTITY_NOT_FOUND));
        Follow follow = new Follow();
        follow.setFollower(follower);
        follow.setFollowed(followed);
        followRepository.save(follow);
    }

    // 언팔로우 기능
    @Transactional
    public void unfollowUser(FollowRequest request) {
        followRepository.deleteByFollowerIdAndFollowedId(request.getFollowerId(), request.getFollowedId());
    }

    // 알림 조회
    @Transactional(readOnly = true)
    public List<Notification> getNotifications(Long userId) {
        return notificationRepository.findByUserIdAndIsReadFalse(userId);
    }
}
