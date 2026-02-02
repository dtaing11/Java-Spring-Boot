package com.zentra.zentra.domain.Comments;


import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CommentsService {

    private final CommentsRepository commentsRepository;

    public CommentsService(CommentsRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }

    public Comments create(UUID userId, String comments, UUID postId){
        Comments comment  = new Comments(
                comments,
                userId,
                postId
        );
        return commentsRepository.save(comment);
    }

    public Comments update(UUID userId, UUID commentId, String comment,UUID postId){
        Comments comments = commentsRepository.findById(commentId).orElseThrow(() -> new EntityNotFoundException("Comment not found"));
        if(!comments.getUserId().equals(userId)){
            throw new AccessDeniedException("Only owner can update comments information");
        }
        if(!comments.getPostId().equals(postId)){
            throw new AccessDeniedException("Only owner can update comments information");
        }
        comments.setComment(comment);
        return commentsRepository.save(comments);

    }

    @Transactional(rollbackOn =  Exception.class)
    public void delete(UUID userId, UUID commentId){
        Comments comments = commentsRepository.findById(commentId).orElseThrow(() -> new EntityNotFoundException("Comment not found"));
        if(!comments.getUserId().equals(userId)){
            throw new AccessDeniedException("Only owner can delete comments information");
        }
        commentsRepository.delete(comments);
    }

}
