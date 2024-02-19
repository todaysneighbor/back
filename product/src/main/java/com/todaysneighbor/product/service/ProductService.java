package com.todaysneighbor.product.service;

import com.todaysneighbor.product.domain.entity.Product;
import com.todaysneighbor.product.domain.entity.TradeStatus;
import com.todaysneighbor.product.domain.repository.ProductRepository;
import com.todaysneighbor.product.dto.*;
import com.todaysneighbor.product.exception.ErrorCode;
import com.todaysneighbor.product.exception.business.BusinessException;
import com.todaysneighbor.product.exception.business.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public ProductDetailResponse findProduct(Long productId) {
        log.debug("service -> productId:" + productId);
        Product product = findByIdNotDeleted(productId);
        isValidProduct(product);
        return ProductDetailResponse.of(product);
    }

    //판매중인 상품
    @Transactional(readOnly = true)
    public List<ProductSummary> findSellingProduct() {
        return productRepository.findAll().stream()
                .filter(product -> !isDeleted(product) && !isSold(product))
                .map(ProductSummary::of)
                .toList();
    }

    //삭제상품 제외 모든 상품
    @Transactional(readOnly = true)
    public List<ProductSummary> findAll() {
        return productRepository.findAll().stream()
                .filter(product -> !product.getIsDeleted())
                .map(ProductSummary::of)
                .toList();
    }


    //등록
    @Transactional
    public Long createProduct(ProductCreateRequest request, Long userId) {
        log.debug("create 시작");
        Product product = ProductCreateRequest.toEntity(request, userId);
        return productRepository.save(product).getId();
    }

    //수정
    @Transactional
    public void update(ProductUpdateRequest request, Long userId, Long productId) {
        Product product = findByIdNotDeleted(productId);
        if (!product.getUserId().equals(userId)) {
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
        Long areaId = 2L;
        Optional.ofNullable(request.getPrice()).ifPresent(product::setPrice);
        Optional.ofNullable(request.getFilename()).ifPresent(product::setFilename);
        Optional.ofNullable(request.getTitle()).ifPresent(product::setTitle);
        Optional.ofNullable(request.getContent()).ifPresent(product::setContent);
        Optional.ofNullable(request.getArea()).ifPresent(area -> product.setAreaId(areaId));
        productRepository.save(product);
    }

    @Transactional
    public void delete(Long productId, Long userId) {
        Product product = findByIdNotDeleted(productId);
        if (!isValidUser(userId, product.getUserId())) {
            throw new BusinessException(ErrorCode.NO_AUTH);
        }

        product.setIsDeleted(true);
        productRepository.save(product);
    }

    @Transactional
    public void tradeComplete(TradeCompleteRequest request, Long productId) {
        Product product = findById(productId);
        isValidProduct(product);
        product.setTradeStatus(TradeStatus.builder()
                .buyerId(request.getBuyerId())
                .build());

    }

    @Transactional(readOnly = true)
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.ENTITY_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public Product findByIdNotDeleted(Long id) {
        return productRepository.findById(id)
                .filter(product -> !isDeleted(product))
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.ENTITY_NOT_FOUND));
    }

    //팔린 상품, 삭제된 상품인지 검사 후 Exception
    private void isValidProduct(Product product) {
        if (isDeleted(product)) {
            throw new EntityNotFoundException(ErrorCode.DELETED_DATA);
        } else if (isSold(product)) {
            throw new EntityNotFoundException(ErrorCode.SOLD_PRODUCT);
        }
    }

    //판매 상품인지 확인
    private boolean isSold(Product product) {
        return product.getIsSold() == null || product.getIsSold();
    }

    //삭제 상품인지 확인
    private boolean isDeleted(Product product) {
        return product.getIsDeleted() == null || product.getIsDeleted();
    }

    private boolean isValidUser(Long requestUserId, Long userId) {
        return requestUserId.equals(userId);
    }

}
