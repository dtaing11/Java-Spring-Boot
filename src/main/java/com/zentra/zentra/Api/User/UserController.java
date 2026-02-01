package com.zentra.zentra.Api.User;

import com.zentra.zentra.Api.User.Request.RegisterRequest;
import com.zentra.zentra.Api.User.Request.UpdateUserRequest;
import com.zentra.zentra.Api.User.Response.RegisterResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zentra.zentra.domain.User.UserService;
import com.zentra.zentra.domain.User.User;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register (@RequestBody RegisterRequest req) {
        User user = userService.registerService(
            req.firstName(),
            req.lastName(),
            req.email(),
            req.password(),
            req.phoneNumber()
        );
        
        return ResponseEntity.ok(new RegisterResponse(
            user.getId(),
            user.getEmail()
        ));
    }
    @PostMapping("/updateUser")
    public ResponseEntity<?> updateUser (@RequestBody UpdateUserRequest req){
        User user = userService.updateUser(
                req.id(),
                req.first_name(),
                req.last_name(),
                req.phoneNumber()
        );
        return ResponseEntity.ok("Information has been updated");
    }
    

}
