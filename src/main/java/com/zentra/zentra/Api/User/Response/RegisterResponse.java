package com.zentra.zentra.Api.User.Response;

import java.util.UUID;

public record RegisterResponse(
    UUID userId,
    String email
) {}
