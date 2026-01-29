package com.zentra.zentra.domain.Orgs.roles;


import jakarta.transaction.Transactional;
import org.hibernate.sql.Update;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrgsRoleService {
    private OrgRolesRepository  orgRolesRepository;

    public OrgsRoleService(OrgRolesRepository orgRolesRepository) {
        this.orgRolesRepository = orgRolesRepository;
    }

    public OrgRoles findUserRole(UUID TargetId, UUID OrgId) {
        Roles targetRole = orgRolesRepository.findById(TargetId).orElseThrow(()-> new AccessDeniedException("Target role not found"));
        return targetRole.getRoleName();
    }
    public Roles createRole(UUID user, OrgRoles roles, UUID orgId) {
        Roles orgroles = new Roles(
                roles,
                user,
                orgId
        );
        return orgRolesRepository.save(orgroles);
    }

    @Transactional
    public Roles updateRole(UUID targetUserId, OrgRoles newRole, UUID orgId, UUID updaterUserId) {

        Roles updaterRole = orgRolesRepository
                .findByUserIdAndOrgId(updaterUserId, orgId)
                .orElseThrow(() -> new AccessDeniedException("Updater is not a member of this org"));

        boolean updaterIsOwnerOrAdmin =
                updaterRole.getRoleName() == OrgRoles.owner ||
                        updaterRole.getRoleName() == OrgRoles.admin;

        if (!updaterIsOwnerOrAdmin) {
            throw new AccessDeniedException("Only OWNER or ADMIN can update roles");
        }

        if (updaterRole.getRoleName() == OrgRoles.admin && updaterUserId.equals(targetUserId)) {
            throw new AccessDeniedException("Admin cannot update their own role");
        }
        Roles targetRole = orgRolesRepository
                .findByUserIdAndOrgId(targetUserId, orgId)
                .orElseThrow(() -> new RuntimeException("Target role not found"));

        if (updaterRole.getRoleName() == OrgRoles.admin && targetRole.getRoleName() == OrgRoles.owner) {
            throw new AccessDeniedException("Admin cannot change the owner's role");
        }

        targetRole.setRoleName(newRole);
        return orgRolesRepository.save(targetRole);
    }

}
