package com.zentra.zentra.domain.Orgs;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name="orgs")
@Getter
@Setter
@AllArgsConstructor
public class Orgs {

    @Id
    @GeneratedValue
    @Column(name = "org_id", nullable = false, updatable = false)
    private UUID id;
    @Column(name="name", nullable = false)
    private String name;
    @Column(name="description", nullable = true)
    private String description;
    @Column(name="created_at", nullable = false)
    private OffsetDateTime createdAt;


    protected Orgs(){
    }
    public Orgs (String name, String description, OffsetDateTime createdAt) {
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
    }
    public Orgs(String name , OffsetDateTime createdAt) {
        this.name = name;
        this.createdAt = createdAt;
    }

}
