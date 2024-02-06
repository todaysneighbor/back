package com.todaysneighbor.product.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long areaId;
    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long categoryId;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @Column
    private Integer price;
    @Column
    private String filename;

    @Column
    private Boolean isSold;
    @Column(nullable = false)
    private Integer viewCount = 0;
    @Column
    private Integer wishCount = 0;

    @Column
    @CreatedDate
    private LocalDateTime createdAt;
    @Column
    @LastModifiedDate
    private LocalDateTime refreshedAt;

    @Column
    private Boolean isDeleted;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private TradeStatus tradeStatus;
}
