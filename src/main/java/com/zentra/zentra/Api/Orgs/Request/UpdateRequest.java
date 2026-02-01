package com.zentra.zentra.Api.Orgs.Request;

import java.util.Optional;
import java.util.UUID;

public record UpdateRequest (

        UUID orgId,
        Optional<String> name,
        Optional<String> description

){ }
