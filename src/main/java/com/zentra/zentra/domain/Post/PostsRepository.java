package com.zentra.zentra.domain.Post;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PostsRepository extends JpaRepository<Posts, UUID> {

    Optional<List<Posts>> findAllByOrgIdAndUserId(UUID orgId, UUID userId);
    Optional<List<Posts>> finaAllByOrgId(UUID orgId);
}
