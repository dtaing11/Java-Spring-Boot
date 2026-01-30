package com.zentra.zentra.api.auth;

import com.zentra.zentra.helper.IpAddressRetrieve;
import com.zentra.zentra.domain.Auth.AuthService;
import com.zentra.zentra.domain.Auth.UserSession;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.time.Duration;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthService authService;
    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody AuthLoginRequest authLoginRequest, HttpServletRequest httpRequest ) {
        InetAddress inetAddress = null;

        try {
            inetAddress = IpAddressRetrieve.getClientInetAddress(httpRequest);
            System.out.println(inetAddress.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        UserSession userSession = authService.loginService(
                authLoginRequest.email(),
                authLoginRequest.password(),
                inetAddress
                );
        ResponseCookie cookie = ResponseCookie.from("session", userSession.getId().toString())
                .httpOnly(true)
                .secure(false)          // false only for local HTTP
                .sameSite("Lax")
                .path("/")
                .maxAge(Duration.ofDays(30))
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body("Cookie has been set");
    }


}
