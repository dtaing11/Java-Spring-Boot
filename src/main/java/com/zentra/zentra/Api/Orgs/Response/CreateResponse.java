package com.zentra.zentra.Api.Orgs.Response;

import java.util.UUID;

public record CreateResponse(
        UUID orgId,
        String name,
        String statusUpdate

) {
}
