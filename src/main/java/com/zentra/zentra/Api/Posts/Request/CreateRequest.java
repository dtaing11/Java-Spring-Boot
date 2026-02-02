package com.zentra.zentra.Api.Posts.Request;

import java.util.Optional;
import java.util.UUID;

public record CreateRequest(
        String title,
        Optional<String> description,
        UUID orgId
) {
}
