package com.todaysneighbor.product.controller;

import com.todaysneighbor.product.domain.repository.ProductRepository;
import com.todaysneighbor.product.dto.*;
import com.todaysneighbor.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    ResponseEntity<ProductDetailResponse> getProductDetail(@PathVariable Long productId){
        return ResponseEntity.status(HttpStatus.OK).body(productService.findProductDetail(productId));
    }

    //판매중인 모든 리스트 조회
    @GetMapping(value = "/selling")
    ResponseEntity<List<ProductListResponse>> getSellingProductsList(){
        return ResponseEntity.status(HttpStatus.OK).body(productService.findSellingProduct());
    }

    //판매중 + 판매 완료된 모든 리스트 조회
    @GetMapping
    ResponseEntity<List<ProductListResponse>> getAllProducts(){
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAll());
    }

//    //토큰 로직 필요
//    @PostMapping
//    ResponseEntity<String> registerProduct(@RequestBody ProductRegistrationRequest request){
//        String token = "";
//        String userId = "test";
//        productService.postProduct(request,userId);
//        return ResponseEntity.status(HttpStatus.CREATED).body("물품 등록 완료");
//    }
//
//    @PatchMapping(value = "/{productId}")
//    ResponseEntity<ProductUpdateResponse> updateProduct(@PathVariable Long productId, @RequestBody ProductUpdateRequest){
//        return ResponseEntity.status(HttpStatus.CREATED).body()
//    }
//
//    @DeleteMapping(value = "/{productId}")
//    ResponseEntity<String> deleteProduct(@PathVariable Long productId){
//
//    }
//
//    @PatchMapping(value = "/complete/{productId}")
//    ResponseEntity<String> tradeComplete(@PathVariable Long productId){
//
//    }
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
