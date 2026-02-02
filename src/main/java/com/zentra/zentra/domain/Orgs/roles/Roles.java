package com.zentra.zentra.domain.Orgs.roles;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name="org_roles")
@AllArgsConstructor
public class Roles {

    @Id
    @Column(name="role_id",  nullable = false,updatable = false)
    @GeneratedValue
    private UUID id;
    @Column(name="org_role", columnDefinition="roles", nullable = false)
    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private OrgRoles roleName;
    @Column(name= "user_id", nullable = false)
    private UUID userId;
    @Column(name= "org_id", nullable = false)
    private UUID orgId;
    @Column(name= "updated_at")
    private OffsetDateTime updateAt;
    protected Roles() {}
    public Roles(OrgRoles roleName, UUID userId, UUID orgId) {
        this.roleName = roleName;
        this.userId = userId;
        this.orgId = orgId;
        this.updateAt = OffsetDateTime.now();
    }

}

