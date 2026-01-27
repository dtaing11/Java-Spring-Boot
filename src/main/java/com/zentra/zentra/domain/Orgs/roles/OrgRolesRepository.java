package com.zentra.zentra.domain.Orgs.roles;

import com.zentra.zentra.domain.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrgRolesRepository extends JpaRepository<Roles, UUID> {
    Optional<Roles> findByUserIdAndOrgId(UUID userId, UUID orgId);

}
