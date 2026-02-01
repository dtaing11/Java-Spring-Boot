package com.zentra.zentra.Api.Orgs.Roles.Request;

import com.zentra.zentra.domain.Orgs.roles.OrgRoles;

import java.util.UUID;

public  record UpdateRequest (
        OrgRoles orgRoles,
        UUID targetID,
        UUID orgID
){
}