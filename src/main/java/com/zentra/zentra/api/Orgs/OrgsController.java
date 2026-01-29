package com.zentra.zentra.api.Orgs;

import com.zentra.zentra.domain.Orgs.Orgs;
import com.zentra.zentra.domain.Orgs.OrgsService;
import com.zentra.zentra.domain.User.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

@RestController()
@RequestMapping("/orgs")
public class OrgsController{


    private OrgsService orgsService;

    public OrgsController(OrgsService orgsService) {
        this.orgsService = orgsService;
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

    @PostMapping("/update")
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
