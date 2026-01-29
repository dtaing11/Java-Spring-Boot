package com.zentra.zentra.api.Orgs;

import java.util.Optional;
import java.util.UUID;

public record UpdateRequest (

        UUID orgId,
        Optional<String> name,
        Optional<String> description

){ }
