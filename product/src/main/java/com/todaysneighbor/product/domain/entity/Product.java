package com.todaysneighbor.product.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
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
    @ColumnDefault("0")
    private Integer viewCount;
    @Column
    @ColumnDefault("0")
    private Integer wishCount;

    @Column
    @CreatedDate
    private LocalDateTime createdAt;
    @Column
    @LastModifiedDate
    private LocalDateTime refreshedAt;

    @Column
    @ColumnDefault("false")
    private Boolean isSold;
    @Column
    @ColumnDefault("false")
    private Boolean isDeleted;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private TradeStatus tradeStatus;
}
