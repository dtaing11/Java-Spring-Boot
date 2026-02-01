package com.zentra.zentra.Api.User.Request;

import java.util.Optional;

public record RegisterRequest(
    String firstName,
    String lastName,
    String email,
    String password,
    Optional<String> phoneNumber
) {}
