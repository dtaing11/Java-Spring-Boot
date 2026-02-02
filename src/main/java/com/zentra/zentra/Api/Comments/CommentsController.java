package com.zentra.zentra.Api.Comments;


import com.zentra.zentra.Api.Comments.Request.CreateRequest;
import com.zentra.zentra.Api.Comments.Request.UpdateRequest;
import com.zentra.zentra.domain.Comments.Comments;
import com.zentra.zentra.domain.Comments.CommentsService;
import com.zentra.zentra.domain.User.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/comments")
public class CommentsController {
private final CommentsService commentsService;
    public CommentsController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> saveComments(@AuthenticationPrincipal User user, @RequestBody CreateRequest createRequest){
        Comments comment = commentsService.create(user.getId(), createRequest.comments(), createRequest.postId() );
        return ResponseEntity.ok("Comment Post Successfully");
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateComments(@AuthenticationPrincipal User user, @RequestBody UpdateRequest updateRequest){
        Comments comments = commentsService.update(user.getId(), updateRequest.commentId(),updateRequest.comment(), updateRequest.postId());
        return ResponseEntity.ok("Comment Update Successfully");
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteComments (@AuthenticationPrincipal User user, @RequestBody UUID commentId){
        commentsService.delete(user.getId(), commentId);
        return ResponseEntity.ok("Comment Delete Successfully");
    }
}
