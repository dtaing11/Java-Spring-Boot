package com.zentra.zentra.domain.Post;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name="posts")
@Getter
@Setter
public class Posts {

    @Id
    @GeneratedValue
    @Column(name="post_id", updatable = false, nullable = false)
    private UUID id;
    @Column(name="post_title",nullable = false)
    private String title;
    @Column(name="description", nullable = true)
    private String description;
    @Column(name="created_at", nullable = false)
    private OffsetDateTime createdAt;
    @Column (name="org_id", nullable = false, updatable = false)
    private UUID orgId;
    @Column(name="user_id", nullable = false,  updatable = false)
    private UUID userId;

    public Posts(String title, String description, UUID orgId, UUID userId) {
        this.title = title;
        this.description = description;
        this.orgId = orgId;
        this.createdAt = OffsetDateTime.now();
        this.userId = userId;
    }

    public Posts(String title, UUID orgId, UUID userId) {
        this.title = title;
        this.orgId = orgId;
        this.createdAt = OffsetDateTime.now();
        this.userId = userId;
    }

}
