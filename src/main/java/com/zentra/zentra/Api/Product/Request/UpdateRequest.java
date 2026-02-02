package com.zentra.zentra.Api.Product.Request;

import java.util.Optional;
import java.util.UUID;

public record UpdateRequest(
        Optional<String> name,
        Optional<String> description,
        UUID pd_id
) {
}
