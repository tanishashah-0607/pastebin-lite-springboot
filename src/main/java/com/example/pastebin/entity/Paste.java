package com.example.pastebin.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "pastes")
public class Paste {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, length = 10000)
    private String content;

    private Integer maxViews;
    private Integer remainingViews;

    private Instant expiresAt;
    private Instant createdAt;

    // getters & setters

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Integer getMaxViews() {
        return maxViews;
    }

    public Integer getRemainingViews() {
        return remainingViews;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setMaxViews(Integer maxViews) {
        this.maxViews = maxViews;
    }

    public void setRemainingViews(Integer remainingViews) {
        this.remainingViews = remainingViews;
    }

    public void setExpiresAt(Instant expiresAt) {
        this.expiresAt = expiresAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}

