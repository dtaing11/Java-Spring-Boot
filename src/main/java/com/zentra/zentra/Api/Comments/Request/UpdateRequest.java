package com.zentra.zentra.Api.Comments.Request;

import java.util.UUID;

public record UpdateRequest(
        UUID commentId,
        String comment,
        UUID postId
) {
}
