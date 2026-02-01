package com.zentra.zentra.domain.Product;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue
    @Column(name="pd_id", nullable = false)
    private UUID id;
    @Column(name="pd_name", nullable = false)
    private String name;
    @Column(name = "pd_description", nullable = true)
    private String description;
    @Column(name="created_at", nullable = false)
    private OffsetDateTime createdAt;
    @Column (name = "created_by", nullable = false)
    private UUID createdBy;
    @Column (name = "org_id",  nullable = false)
    private UUID orgId;

    public Product(String name, String description, UUID createdBy, UUID orgId) {
        this.name = name;
        this.description = description;
        this.createdAt = OffsetDateTime.now();
        this.createdBy = createdBy;
        this.orgId = orgId;
    }

    public Product(String name, UUID createdBy, UUID orgId) {
        this.name = name;
        this.createdAt = OffsetDateTime.now();
        this.createdBy = createdBy;
        this.orgId = orgId;
    }
}
