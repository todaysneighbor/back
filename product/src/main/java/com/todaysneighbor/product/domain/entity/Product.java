package com.todaysneighbor.product.domain.entity;

import jakarta.persistence.*;
import lombok.*;

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
    private Integer sellPrice;
    @Column
    private String filename;

    @Column
    private Boolean isSold;
    @Column(nullable = false)
    private Integer viewCount;
    @Column
    private LocalDateTime createdAt;
    @Column
    private LocalDateTime refreshedAt;
    @Column
    private Boolean isDeleted;
    @Column
    private Integer wishCount = 0;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private TradeStatus tradeStatus;
}
