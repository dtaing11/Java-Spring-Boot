package com.zentra.zentra.domain.EmailList;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name="email_list")
@Getter
@Setter
public class EmailList {
    @Id
    @GeneratedValue
    @Column(name="email_id", nullable = false, updatable = false)
    private UUID id;
    @Column(name = "email",  nullable = false)
    private String email;
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;
    @Column(name = "pd_id", nullable = false)
    private UUID pdId;
    @Column(name = "user_id" , nullable = false)
    private UUID userId;
    public EmailList(String email, UUID pdId, UUID userId) {
        this.email = email;
        this.pdId = pdId;
        this.userId = userId;
        this.createdAt = OffsetDateTime.now();
    }
}
