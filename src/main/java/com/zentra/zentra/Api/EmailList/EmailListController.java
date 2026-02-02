package com.zentra.zentra.Api.EmailList;


import com.zentra.zentra.Api.EmailList.Request.SignUpRequest;
import com.zentra.zentra.domain.EmailList.EmailList;
import com.zentra.zentra.domain.EmailList.EmailListService;
import com.zentra.zentra.domain.Product.ProductSerivce;
import com.zentra.zentra.domain.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/EmailList")
public class EmailListController {
    @Autowired
    private final EmailListService emailListService;
    private final ProductSerivce productSerivce;

    public EmailListController( EmailListService emailListService, ProductSerivce productSerivce) {
        this.emailListService = emailListService;
        this.productSerivce = productSerivce;
    }
    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@AuthenticationPrincipal User user, @RequestBody SignUpRequest signUpRequest){
        emailListService.signUp(
                user.getId(),
                signUpRequest.email(),
                signUpRequest.pdId()
        );

        return ResponseEntity.ok("Sign up successful");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@AuthenticationPrincipal User user, @RequestBody UUID pdId){
        emailListService.delete(
                user.getId(),
                pdId
        );
        return ResponseEntity.ok("Delete successful from:" + productSerivce.findById(pdId).getName());
    }

    @GetMapping("/{pd_Id}")
    public ResponseEntity<?> getEmailList(@AuthenticationPrincipal User user, @PathVariable("pd_Id") UUID pdId){
         List<EmailList> emailListList =  emailListService.findbyProduct(user.getId(),pdId);

         if(emailListList.isEmpty()){
             return ResponseEntity.notFound().build();
         }
         return ResponseEntity.ok(emailListList);
    }




}
