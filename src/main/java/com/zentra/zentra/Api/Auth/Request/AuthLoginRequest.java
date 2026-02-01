package com.zentra.zentra.Api.Auth.Request;

public record AuthLoginRequest(
        String email,
        String password
) {
}
