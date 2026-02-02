package com.zentra.zentra.domain.Post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PostsRepository extends JpaRepository<Posts, UUID> {

    Optional<List<Posts>> findAllByOrgIdAndUserId(UUID orgId, UUID userId);
    Optional<List<Posts>> findAllByOrgId(UUID orgId);

    @Query("SELECT p.id AS postId, p.title AS postTitle FROM Posts p")
    List<PostIdAndTitle> findAllPostIdAndTitle ();
}
