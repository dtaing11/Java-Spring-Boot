package com.zentra.zentra.Api.Posts;


import com.zentra.zentra.Api.Posts.Request.CreateRequest;
import com.zentra.zentra.domain.Post.Posts;
import com.zentra.zentra.domain.Post.PostsService;
import com.zentra.zentra.domain.User.User;
import com.zentra.zentra.helper.NameUUID;
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

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<?> deleteById(@AuthenticationPrincipal User user, @PathVariable UUID postId) {
        postsService.deleteById(
                user.getId(),
                postId
        );
        return ResponseEntity.ok().build();
    }
    @GetMapping("/find/{orgId}")
    public ResponseEntity<List<Posts>> findAllByOrgId(@PathVariable("orgId") UUID orgId) {
        List<Posts> posts = postsService.findAllByOrgId(orgId);
        return ResponseEntity.ok(posts);

    }

    @GetMapping("/find/{orgId}/{userId}")
    public ResponseEntity<List<Posts>> findAllbyUserIdAndOrgId(@PathVariable("orgId") UUID orgId, @PathVariable("userId") UUID userId) {
        List<Posts> posts = postsService.findAllByUserIdAndOrgId(userId, orgId);
        return ResponseEntity.ok(posts);
    }
    @GetMapping("/find/id/{id}")
    public ResponseEntity<Posts> findById(@PathVariable("id") UUID id) {
        Posts post = postsService.findById(id);
        return ResponseEntity.ok(post);
    }

    @GetMapping("/search")
    public ResponseEntity<?> findBySearch(@RequestParam ("search")String search){
        List<NameUUID> result = postsService.findPostBySearch(search);
        return ResponseEntity.ok(result);
    }

}
