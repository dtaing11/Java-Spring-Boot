package com.zentra.zentra.api.user;

import java.util.Optional;

public record RegisterRequest(
    String firstName,
    String lastName,
    String email,
    String password,
    Optional<String> phoneNumber
) {}
