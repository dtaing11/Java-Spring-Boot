package com.zentra.zentra.domain.Auth;

import java.net.InetAddress;
import java.time.OffsetDateTime;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="user_sessions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSession {

    @Id
    @Column(name="session_id", nullable = false, updatable = false)
    @GeneratedValue
    private UUID id;
    @Column(name="created_at", nullable = false,  updatable = false)
    private OffsetDateTime created_at;
    @Column(name = "expired_at", nullable = false, updatable = false)
    private OffsetDateTime expired_at; 
    @Column(name = "user_id", nullable = false, updatable = false)
    private UUID user_id;
    @Column(name = "ip_address", nullable = false, updatable = false)
    private InetAddress ipAddress;


}
