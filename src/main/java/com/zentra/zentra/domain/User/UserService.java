package com.zentra.zentra.domain.User;

import java.util.Optional;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService  {

    private final UserRepository userRepository; 
    private final PasswordEncoder passwordEncoder; 

  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public User registerService (String first_name, String last_name, String email, String password, Optional<String> phoneNumber ){
     String normalizedEmail = email.trim().toLowerCase();

    if (userRepository.findByEmail(normalizedEmail).isPresent() ) {
      throw new IllegalArgumentException("Email already in use");
    }

     User u = new User();
     u.setFirstName(first_name);
     u.setLastName(last_name);
     u.setEmail(normalizedEmail);
     u.setPasswordHash(passwordEncoder.encode(password));
    if (phoneNumber.isPresent()) {
     u.setPhoneNumber(phoneNumber.toString());
    }
    return userRepository.save(u);
  }

  public User getById(UUID userId) {
    return userRepository.findById(userId)
        .orElseThrow(() -> new IllegalArgumentException("User not found"));
  }

  public User updateUser(UUID userId, String firstName, String lastName, String phoneNumber) {
    User u = getById(userId);
    if (firstName != null){
        u.setFirstName(firstName);
    }
    if (lastName != null ){
        u.setLastName(lastName);
    }
    if(phoneNumber != null){
        u.setPhoneNumber(phoneNumber);
    }
    return userRepository.save(u);
  }

    public void deleteUser(UUID userId) {
    userRepository.deleteById(userId); 
  }

 
}
