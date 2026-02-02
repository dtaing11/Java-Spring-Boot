package com.zentra.zentra.domain.Comments;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface CommentsRepository extends JpaRepository<Comments, UUID> {
}
