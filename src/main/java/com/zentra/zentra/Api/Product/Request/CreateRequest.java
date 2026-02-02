package com.zentra.zentra.Api.Product.Request;

import java.util.Optional;
import java.util.UUID;

public record CreateRequest (
        String name,
        Optional<String> description,
        UUID orgId
){
}
