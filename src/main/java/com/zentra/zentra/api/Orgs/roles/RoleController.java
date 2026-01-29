package com.zentra.zentra.api.Orgs.roles;

import com.zentra.zentra.api.Orgs.roles.UpdateRequest;
import com.zentra.zentra.domain.Orgs.roles.OrgsRoleService;
import com.zentra.zentra.domain.Orgs.roles.Roles;
import com.zentra.zentra.domain.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.relation.Role;

@RestController
@RequestMapping("/orgs/roles")
public class RoleController {
    private OrgsRoleService orgsRoleService;
    @Autowired
    public RoleController(OrgsRoleService orgsRoleService) {
        this.orgsRoleService = orgsRoleService;
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateRole(@AuthenticationPrincipal User user, @RequestBody UpdateRequest req) {
        Roles roles = orgsRoleService.updateRole(
                user.getId(),
                req.orgRoles(),
                req.orgID(),
                req.targetID()
        );

        return ResponseEntity.ok("Updated Roles");

    }
}
