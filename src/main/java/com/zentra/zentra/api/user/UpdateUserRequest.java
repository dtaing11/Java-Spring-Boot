package com.zentra.zentra.api.user;

import java.util.UUID;

public record UpdateUserRequest(
        UUID id,
        String first_name,
        String last_name,
        String phoneNumber
) {
}
