package com.zentra.zentra.api.Orgs;

import java.util.Optional;

public record CreateRequest(
        String name,
        Optional<String> description
) {}