package com.zentra.zentra.api.auth;

public record AuthLoginRequest(
        String email,
        String password
) {
}
