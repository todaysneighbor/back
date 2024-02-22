package com.todaysneighbor.auth.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="providerId")
    private long providerId;

    @Column(name = "nickname", nullable = false, length = 10)
    private String nickname;

    @Column(name = "filename", length = 100)
    private String filename;

    @Column(name = "rating", nullable = false)
    private float rating = 0;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "is_deleted")
    private boolean isDeleted = false;

}
