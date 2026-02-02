package com.zentra.zentra.Api.EmailList.Request;

import java.util.UUID;

public record SignUpRequest
        (
                String email,
                UUID pdId
        ){
}
