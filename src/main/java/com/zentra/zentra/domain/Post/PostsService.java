package com.zentra.zentra.domain.Post;


import com.zentra.zentra.domain.Orgs.roles.OrgsRoleService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostsService {
    private final PostsRepository postsRepository;
    private final OrgsRoleService orgsRoleService;
    public PostsService(PostsRepository postsRepository,  OrgsRoleService orgsRoleService) {
        this.postsRepository = postsRepository;
        this.orgsRoleService = orgsRoleService;
    }
    @Transactional
    public Posts create(UUID userId, String postTitle, Optional<String> postDescription, UUID orgId) {
        orgsRoleService.findUserRole(userId,orgId);
        Posts post = new Posts(
                postTitle,
                postDescription.orElse(null),
                orgId,
                userId
        );
        return postsRepository.save(post);
    }

    public Posts findById(UUID postId) {
        return postsRepository.findById(postId).orElse(null);
    }

    public List<Posts> findAllByUserIdAndOrgId(UUID userId, UUID orgId) {
        return postsRepository.findAllByOrgIdAndUserId(orgId, userId).orElse(null);
    }
    public List<Posts> findAllByOrgId(UUID orgId) {
        return postsRepository.finaAllByOrgId(orgId).orElse(null);
    }




}
