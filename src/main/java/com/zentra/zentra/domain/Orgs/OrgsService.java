package com.zentra.zentra.domain.Orgs;

import com.zentra.zentra.domain.Orgs.roles.OrgRoles;
import com.zentra.zentra.domain.Orgs.roles.OrgRolesRepository;
import com.zentra.zentra.domain.Orgs.roles.OrgsRoleService;
import com.zentra.zentra.domain.Orgs.roles.Roles;
import jakarta.transaction.Transactional;
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
    public OrgsService(OrgsRepository orgsRepository, OrgsRoleService orgsRoleService) {
        this.orgsRepository = orgsRepository;
        this.orgsRoleService = orgsRoleService;

    }

    public Orgs findById(UUID id) {
        return orgsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Orgs not found"));
    }


    @Transactional
    public Orgs updateOrgsService(UUID updaterId, UUID orgID, Optional<String> name, Optional<String> description){
        System.out.println("HIT service org ");
        System.out.println(updaterId);

        OrgRoles role;
        try {
            role = orgsRoleService.findUserRole(updaterId, orgID);
            System.out.println("ROLE CHECK updaterId=" + updaterId + " orgID=" + orgID + " role=" + role);
        } catch (Exception e) {
            e.printStackTrace(); // <-- you NEED this right now
            throw e;
        }

        Orgs orgs = orgsRepository.findById(orgID)
                .orElseThrow(() -> new AccessDeniedException("Org not found"));

        if (role != OrgRoles.owner) {
            throw new AccessDeniedException("Only owner can update orgs information");
        }

        name.ifPresent(orgs::setName);
        description.ifPresent(orgs::setDescription);
        return orgsRepository.save(orgs);
    }

    @Transactional
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
