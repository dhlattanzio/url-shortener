package com.dhlattanzio.urlshortener.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
public class ShortenedUrl extends PanacheEntityBase {
    @Id
    public String keyword;

    public String url;

    public Integer hits = 0;

    @CreationTimestamp
    @Column(updatable = false)
    public Instant createdAt;

    @UpdateTimestamp
    public Instant updatedAt;
}
