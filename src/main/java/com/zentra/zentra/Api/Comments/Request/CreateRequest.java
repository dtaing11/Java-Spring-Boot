package com.zentra.zentra.Api.Comments.Request;

import java.util.UUID;

public record CreateRequest(
        String comments,
        UUID postId
) {
}
