package com.zentra.zentra.domain.User;

import java.time.OffsetDateTime;
import java.util.UUID;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
@Table(name="users")
public class User {
    @GeneratedValue
    @Id
    @Column(name = "user_id", nullable = false, updatable = false)
    private UUID id;
    @Column(name = "first_name", nullable = false)
    private String firstName; 
    @Column(name = "last_name", nullable = false)
    private String lastName; 
    @Column(name = "email", nullable = false)
    private String email; 
    @Column(name = "password_hash", nullable = false)
    private String passwordHash; 
    @Column(name="phone_number", columnDefinition="us_phone_number")
    private String phoneNumber; 
    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    protected User(){}

    public User(String firstName, String lastName, String email, String passwordHash, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.phoneNumber = phoneNumber;
    }
    public User(String firstName, String lastName, String email, String passwordHash) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.passwordHash = passwordHash;
    }
}
