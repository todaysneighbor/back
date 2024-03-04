package com.todaysneighbor.user.controller;

import com.todaysneighbor.user.dto.*;
import com.todaysneighbor.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 리뷰 작성
    @PostMapping("/{userId}/reviews")
    public ResponseEntity<Void> createReview(@PathVariable Long userId, @RequestBody ReviewCreateRequest request) {
        userService.createReview(userId, request);
        return ResponseEntity.ok().build();
    }

    // 사용자별 리뷰 조회
    @GetMapping("/{userId}/reviews")
    public ResponseEntity<List<ReviewResponse>> getReviewsByUserId(@PathVariable Long userId) {
        List<ReviewResponse> reviews = userService.getReviewsByUserId(userId);
        return ResponseEntity.ok(reviews);
    }

    // 팔로우
    @PostMapping("/follow")
    public ResponseEntity<Void> followUser(@RequestBody FollowRequest request) {
        userService.followUser(request);
        return ResponseEntity.ok().build();
    }

    // 언팔로우
    @DeleteMapping("/unfollow")
    public ResponseEntity<Void> unfollowUser(@RequestBody FollowRequest request) {
        userService.unfollowUser(request);
        return ResponseEntity.ok().build();
    }

    // 위시리스트에 상품 추가
    @PostMapping("/{userId}/wish-products")
    public ResponseEntity<Void> addWishProduct(@PathVariable Long userId, @RequestBody WishProductCreateRequest request) {
        userService.addWishProduct(userId, request);
        return ResponseEntity.ok().build();
    }

    // 위시리스트 조회
    @GetMapping("/{userId}/wish-products")
    public ResponseEntity<List<WishProductResponse>> getWishList(@PathVariable Long userId) {
        List<WishProductResponse> wishProducts = userService.getWishList(userId);
        return ResponseEntity.ok(wishProducts);
    }

    // 키워드 추가
    @PostMapping("/{userId}/keywords")
    public ResponseEntity<Void> addKeyword(@PathVariable Long userId, @RequestBody KeywordCreateRequest request) {
        userService.addKeyword(userId, request);
        return ResponseEntity.ok().build();
    }

    // 키워드 조회
    @GetMapping("/{userId}/keywords")
    public ResponseEntity<List<KeywordResponse>> getKeywordsByUserId(@PathVariable Long userId) {
        List<KeywordResponse> keywords = userService.getKeywordsByUserId(userId);
        return ResponseEntity.ok(keywords);
    }

    // 사용자 상세 정보 조회
    @GetMapping("/{userId}")
    public ResponseEntity<UserDetailResponse> getUserDetails(@PathVariable Long userId) {
        UserDetailResponse userDetails = userService.getUserDetails(userId);
        return ResponseEntity.ok(userDetails);
    }
}
