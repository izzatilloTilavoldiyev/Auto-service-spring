package com.company.autoservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    protected LocalDateTime createdDate;

    @UpdateTimestamp
    @Column(nullable = false)
    protected LocalDateTime updatedDate;
}