package com.zentra.zentra.domain.Post;


import com.zentra.zentra.domain.Orgs.roles.OrgRoles;
import com.zentra.zentra.domain.Orgs.roles.OrgsRoleService;
import com.zentra.zentra.domain.Orgs.roles.Roles;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.security.access.AccessDeniedException;
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
        return postsRepository.findAllByOrgId(orgId).orElse(null);
    }

    @Transactional(rollbackOn = Exception.class)
    public void deleteById(UUID userId,UUID postId) {
        Posts posts = postsRepository.findById(postId).orElseThrow(()-> new EntityNotFoundException("Posts not found"));
        if(posts.getId().equals(userId)) {
            postsRepository.delete(posts);
        }
        if(orgsRoleService.findUserRole(userId, posts.getOrgId()).equals(OrgRoles.owner) || orgsRoleService.findUserRole(userId, posts.getOrgId()).equals(OrgRoles.admin)) {
            postsRepository.delete(posts);
        }
        else {
            throw new AccessDeniedException("You are not allowed to delete this post");
        }
    }




}
