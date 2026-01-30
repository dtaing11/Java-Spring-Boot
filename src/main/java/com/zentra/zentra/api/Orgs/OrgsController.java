package com.zentra.zentra.api.Orgs;

import com.zentra.zentra.domain.Orgs.Orgs;
import com.zentra.zentra.domain.Orgs.OrgsService;
import com.zentra.zentra.domain.User.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.Callable;

@RestController()
@RequestMapping("/orgs")
public class OrgsController{


    private OrgsService orgsService;

    public OrgsController(OrgsService orgsService) {
        this.orgsService = orgsService;
    }

    @GetMapping("/{org_id}")
    public ResponseEntity<Orgs> getOrgById(@PathVariable("org_id") UUID orgId){
        Orgs org = orgsService.findById(orgId);
        return ResponseEntity.ok(org);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@AuthenticationPrincipal User user, @RequestBody CreateRequest req) {
       Orgs orgs =  orgsService.createOrgsService(
               user.getId(),
               req.name(),
               req.description()
        );
       return ResponseEntity.ok( new CreateResponse(
               orgs.getId(),
               orgs.getName(),
               "You've created an Orgs")

       );
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@AuthenticationPrincipal User user, @RequestBody UpdateRequest req) {
        Orgs orgs =  orgsService.updateOrgsService(
                user.getId(),
                req.orgId(),
                req.name(),
                req.description()
        );
        return ResponseEntity.ok("Updated Orgs");
    }

}
