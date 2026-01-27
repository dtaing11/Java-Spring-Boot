package com.zentra.zentra.api.Orgs;

import java.util.UUID;

public record CreateResponse(
        UUID orgId,
        String name,
        String statusUpdate

) {
}
