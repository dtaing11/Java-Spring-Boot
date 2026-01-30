# Zentra Organization Roles API

This module provides APIs for managing and updating roles within an organization.
All endpoints require an authenticated user.

Base path:
```
/orgs/roles
```

---

## Tech Stack

- Java
- Spring Boot
- Spring Web (REST)
- Spring Security
- UUID-based IDs

---

## Authentication

All endpoints require an authenticated user:

```java
@AuthenticationPrincipal User user
```

The authenticated user must have sufficient permissions to update organization roles.

---

## API Endpoints

### Update Organization Role

Update a user’s role within an organization.

Endpoint:
```
PUT /orgs/roles/update
```

Authentication:
- Required

Request Body:
```json
{
  "orgRoles": "ADMIN",
  "targetID": "550e8400-e29b-41d4-a716-446655440000",
  "orgID": "660e8400-e29b-41d4-a716-446655440000"
}
```

Request Fields:

| Field | Type | Required | Description |
|------|------|----------|-------------|
| orgRoles | OrgRoles (Enum) | Yes | Role to assign |
| targetID | UUID | Yes | User ID whose role will be updated |
| orgID | UUID | Yes | Organization ID |

> `orgRoles` must be a valid value from the `OrgRoles` enum.

---

Success Response – 200 OK:
```
Updated Roles
```

---

## DTOs

UpdateRequest:
```java
public record UpdateRequest(
    OrgRoles orgRoles,
    UUID targetID,
    UUID orgID
) {}
```

---

## Notes

- Role updates are scoped to a specific organization.
- The authenticated user must be authorized to update roles.
- Consider returning the updated role object instead of plain text.
- Endpoint could be REST-ified to:
  ```
  PUT /orgs/{orgId}/roles/{userId}
  ```
