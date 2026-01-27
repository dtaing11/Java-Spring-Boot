package com.zentra.zentra.domain.Auth;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSessionRepository extends JpaRepository<UserSession, UUID> {
    @Override
    Optional<UserSession> findById(UUID uuid);
}
