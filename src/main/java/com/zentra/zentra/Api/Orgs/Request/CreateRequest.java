package com.zentra.zentra.Api.Orgs.Request;

import java.util.Optional;

public record CreateRequest(
        String name,
        Optional<String> description
) {}