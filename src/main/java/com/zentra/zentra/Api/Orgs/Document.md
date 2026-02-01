# Zentra Orgs API

This module provides REST APIs for creating and updating organizations (Orgs).
All endpoints require an authenticated user.

Base path:
```
/orgs
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

All endpoints use the authenticated user via:

```java
@AuthenticationPrincipal User user
```

The authenticated user's ID is used to authorize org creation and updates.

---

## API Endpoints

### Create Organization

Create a new organization owned by the authenticated user.

Endpoint:
```
POST /orgs/create
```

Authentication:
- Required

Request Body:
```json
{
  "name": "Zentra Inc",
  "description": "Main Zentra organization"
}
```

Request Fields:

| Field | Type | Required | Description |
|------|------|----------|-------------|
| name | String | Yes | Organization name |
| description | String | No | Optional organization description |

---

Success Response – 200 OK:
```json
{
  "orgId": "550e8400-e29b-41d4-a716-446655440000",
  "name": "Zentra Inc",
  "statusUpdate": "You've created an Orgs"
}
```

Response Fields:

| Field | Type | Description |
|------|------|-------------|
| orgId | UUID | Organization ID |
| name | String | Organization name |
| statusUpdate | String | Status message |

---

### Update Organization

Update an existing organization owned by the authenticated user.

Endpoint:
```
PUT /orgs/update
```

Authentication:
- Required

Request Body:
```json
{
  "orgId": "550e8400-e29b-41d4-a716-446655440000",
  "name": "Zentra Updated",
  "description": "Updated description"
}
```

Request Fields:

| Field | Type | Required | Description |
|------|------|----------|-------------|
| orgId | UUID | Yes | Organization ID |
| name | String | No | Updated name |
| description | String | No | Updated description |

> `name` and `description` are optional. Only provided fields will be updated.

---

Success Response – 200 OK:
---

```
GET /orgs/{org_id}
```

Authentication:
- Required

Response Body:
```json
{
  "orgId": "550e8400-e29b-41d4-a716-446655440000",
  "name": "Zentra Updated",
  "description": "Updated description"
}
```





```

```

---

## DTOs

CreateRequest:
```java
public record CreateRequest(
    String name,
    Optional<String> description
) {}
```

CreateResponse:
```java
public record CreateResponse(
    UUID orgId,
    String name,
    String statusUpdate
) {}
```

UpdateRequest:
```java
public record UpdateRequest(
    UUID orgId,
    Optional<String> name,
    Optional<String> description
) {}
```

---

## Notes

- All organization actions are scoped to the authenticated user.
- All IDs are UUIDs.
- Consider returning structured JSON instead of plain text for update responses.
- Consider using `PUT /orgs/{orgId}` for REST-style updates.
