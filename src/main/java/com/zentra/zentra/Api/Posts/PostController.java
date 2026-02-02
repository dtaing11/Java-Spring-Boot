package com.zentra.zentra.Api.Posts;


import com.zentra.zentra.Api.Posts.Request.CreateRequest;
import com.zentra.zentra.domain.Post.Posts;
import com.zentra.zentra.domain.Post.PostsService;
import com.zentra.zentra.domain.User.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/post")
public class PostController {
    private final PostsService postsService;
    public PostController(PostsService postsService) {
        this.postsService = postsService;
    }

    @PostMapping("/create")
    public ResponseEntity<Posts> create (@AuthenticationPrincipal User user, @RequestBody CreateRequest request) {
        Posts post = postsService.create(
                user.getId(),
                request.title(),
                request.description(),
                request.orgId()
        );

        return ResponseEntity.ok(post);
    }

    @GetMapping("/find/{orgId}")
    public ResponseEntity<List<Posts>> findAllByOrgId(@PathVariable("orgId") UUID orgId) {
        List<Posts> posts = postsService.findAllByOrgId(orgId);
        if (posts == null || posts.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(posts);

    }

    @GetMapping("/find/{orgId}/{userId}")
    public ResponseEntity<List<Posts>> findAllbyUserIdAndOrgId(@PathVariable("orgId") UUID orgId, @PathVariable("userId") UUID userId) {
        List<Posts> posts = postsService.findAllByUserIdAndOrgId(userId, orgId);
        if (posts == null || posts.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(posts);
    }

}
