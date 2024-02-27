package com.todaysneighbor.product.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "wish", indexes = @Index(name="idx_user_id", columnList = "user_id"))
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class Wish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;
    @Column
    private Long productId;

    @CreatedDate
    private LocalDateTime createdAt;
    private Boolean isDeleted;

    @ManyToOne
    @JoinColumn(name="product_id", insertable = false, updatable = false)
    private Product product;
}
