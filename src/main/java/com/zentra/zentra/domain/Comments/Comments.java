package com.zentra.zentra.domain.Comments;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name="comments")
public class Comments {
    @Id
    @GeneratedValue
    @Column(name="comment_id", nullable = false,updatable = false)
    private UUID id;
    @Column(name="comment", nullable = false)
    private String comment;
    @Column(name="updated_at", nullable = false)
    private OffsetDateTime updatedAt;
    @Column(name="user_id", nullable = false)
    private UUID userId;
    @Column(name="post_id", nullable = false)
    private UUID postId;

}
