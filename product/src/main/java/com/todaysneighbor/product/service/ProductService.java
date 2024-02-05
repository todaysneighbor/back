package com.todaysneighbor.product.service;

import com.todaysneighbor.product.domain.entity.Product;
import com.todaysneighbor.product.domain.repository.ProductRepository;
import com.todaysneighbor.product.dto.ProductDetailResponse;
import com.todaysneighbor.product.dto.ProductListResponse;
import com.todaysneighbor.product.exception.ErrorCode;
import com.todaysneighbor.product.exception.business.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public ProductDetailResponse findProductDetail(Long productId) {
        Product product = getProduct(productId);
        return ProductDetailResponse.of(product);
    }

    //판매중인 상품
    @Transactional(readOnly = true)
    public List<ProductListResponse> findSellingProduct() {
        return productRepository.findAll().stream()
                .filter(product -> isValidProduct(product))
                .map(ProductListResponse::of)
                .toList();
    }

    //삭제상품 제외 모든 상품
    @Transactional(readOnly = true)
    public List<ProductListResponse> findAll() {
        return productRepository.findAll().stream()
                .filter(product -> !product.getIsDeleted())
                .map(ProductListResponse::of)
                .toList();
    }


    @Transactional(readOnly = true)
    public Product getProduct(Long id) {
        return productRepository.findById(id)
                .filter(this::isValidProduct)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.ENTITY_NOT_FOUND));
    }

    //팔린 상품, 삭제된 상품인지 검사
    private Boolean isValidProduct(Product product) {
        if (isDeleted(product)) {
            throw new EntityNotFoundException(ErrorCode.DELETED_DATA);
        } else if (isSold(product)) {
            throw new EntityNotFoundException(ErrorCode.SOLD_PRODUCT);
        }
        return true;
    }

    //판매 상품인지 확인
    private boolean isSold(Product product) {
        return product.getIsSold() == null || product.getIsSold();
    }

    //삭제 상품인지 확인
    private boolean isDeleted(Product product) {
        return product.getIsDeleted() == null || product.getIsDeleted();
    }

}
