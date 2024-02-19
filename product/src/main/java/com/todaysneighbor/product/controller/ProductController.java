package com.todaysneighbor.product.controller;

import com.todaysneighbor.product.dto.*;
import com.todaysneighbor.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/product")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class ProductController {
    private final ProductService productService;

    //상세 조회
    @GetMapping(value = "/{productId}")
    ResponseEntity<ProductDetailResponse> findDetail(@PathVariable Long productId) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findProduct(productId));
    }

    //판매중인 모든 리스트 조회
    @GetMapping(value = "/selling")
    ResponseEntity<List<ProductSummary>> findSellingAll() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findSellingProduct());
    }

    //판매중 + 판매 완료된 모든 리스트 조회
    @GetMapping
    ResponseEntity<List<ProductSummary>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAll());
    }

    //토큰 로직 필요
    @PostMapping
    ResponseEntity<Void> save(@Validated @RequestBody ProductCreateRequest request) {
        String token = "";
        Long userId = 1L;
        Long productId = productService.createProduct(request, userId);
        return ResponseEntity.created(URI.create(String.valueOf(productId))).build();

    }

    @PatchMapping(value = "/{productId}")
    ResponseEntity<Void> update(@PathVariable Long productId, @RequestBody ProductUpdateRequest request) {
        String token = "";
        Long userId = 1L;
        productService.update(request, userId, productId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{productId}")
    ResponseEntity<Void> delete(@PathVariable Long productId){
        String token="";
        Long userId=1L;
        productService.delete(productId, userId);
        return ResponseEntity.noContent().build();

    }

    @PostMapping(value = "/trade")
    ResponseEntity<String> tradeComplete(@RequestBody TradeCompleteRequest request){
        productService.tradeComplete(request);
        return ResponseEntity.noContent().build();
    }
//
//    @PostMapping(value = "/{productId}/wish")
//    ResponseEntity<String> wishProductRegistration(@PathVariable Long productId){
//
//    }
//
//    @PatchMapping(value = "/{productId}/wish")
//    ResponseEntity<String> wishProductDelete(@PathVariable Long productId){
//
//    }
//
//    @GetMapping
//    ResponseEntity<ProductSearchResultResponse> searchProduct(@RequestParam String keyword, @RequestParam String category){
//
//    }


}
