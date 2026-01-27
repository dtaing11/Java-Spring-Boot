package com.zentra.zentra.domain.Orgs;

import com.zentra.zentra.domain.Orgs.roles.OrgRoles;
import com.zentra.zentra.domain.Orgs.roles.OrgRolesRepository;
import com.zentra.zentra.domain.Orgs.roles.OrgsRoleService;
import com.zentra.zentra.domain.Orgs.roles.Roles;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrgsService {

    private OrgsRepository orgsRepository;
    private OrgsRoleService orgsRoleService;

    private OrgRolesRepository orgRolesRepository;
    public OrgsService(OrgsRepository orgsRepository, OrgRolesRepository orgRolesRepository) {
        this.orgsRepository = orgsRepository;
        this.orgRolesRepository = orgRolesRepository;

    }

    public Orgs updateOrgsService(UUID updaterId, UUID orgID, Optional<String> name, Optional<String> description){
        Orgs orgs = orgsRepository.findById(orgID).orElseThrow(() -> new AccessDeniedException("Org not found"));
        name.ifPresent(orgs::setName);
        description.ifPresent(orgs::setDescription);
        return orgsRepository.save(orgs);
    }
    public Orgs createOrgsService(UUID userId, String name, Optional<String> description ) {
        String descriptions = description.orElse(null);

        Orgs orgs = new Orgs(
                name,
                descriptions,
                OffsetDateTime.now()
        );
        var orgsRepo = orgsRepository.save(orgs);
        orgsRoleService.createRole(userId, OrgRoles.owner, orgsRepo.getId());
        return orgsRepo;
    }
}
