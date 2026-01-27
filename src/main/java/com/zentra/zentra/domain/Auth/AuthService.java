package com.zentra.zentra.domain.Auth;

import java.net.InetAddress;
import java.time.OffsetDateTime;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.zentra.zentra.domain.User.User;
import com.zentra.zentra.domain.User.UserRepository;

@Service
public class AuthService {
    private UserSessionRepository userSessionRepository;
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public AuthService(UserSessionRepository userSessionRepository, UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userSessionRepository = userSessionRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public UserSession loginService (String email, String password, InetAddress ipAddress){

       User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

        String hash = user.getPasswordHash();
        if(!passwordEncoder.matches(password,hash)){
            throw new RuntimeException("Email or password incorrect");
        }
        UserSession userSession = new UserSession();
        userSession.setCreated_at(OffsetDateTime.now());
        userSession.setExpired_at(OffsetDateTime.now().plusDays(30));
        userSession.setUser_id(user.getId());
        userSession.setIpAddress(ipAddress);
        return userSessionRepository.save(userSession);
    }

    public User resetPassword (String email, String password){
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));
        String hash = user.getPasswordHash();
        user.setPasswordHash(passwordEncoder.encode(password));
        return userRepository.save(user);
    }

    public User sessionAuth(String token) throws Exception{
        UUID sessionID = UUID.fromString(token);
        UserSession userSession = userSessionRepository.findById(sessionID)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (userSession.getExpired_at().isBefore(OffsetDateTime.now())) {
            throw new Exception("User session has expired");
        }
        return userRepository.findById(userSession.getUser_id())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
