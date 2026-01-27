package com.zentra.zentra.api.user;

import java.util.UUID;

public record RegisterResponse(
    UUID userId,
    String email
) {}
