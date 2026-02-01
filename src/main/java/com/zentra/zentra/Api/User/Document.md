# Zentra User API

This module provides REST APIs for user registration and user profile updates.

Base path:
```
/user
```

---

## Tech Stack

- Java
- Spring Boot
- Spring Web (REST)
- UUID-based user IDs

---

## API Endpoints

### Register User

Create a new user account.

Endpoint:
```
POST /user/register
```

Request Body:
```json
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "password": "StrongPassword123",
  "phoneNumber": "1234567890"
}
```

Request Fields:

| Field | Type | Required | Description |
|------|------|----------|-------------|
| firstName | String | Yes | User's first name |
| lastName | String | Yes | User's last name |
| email | String | Yes | User's email |
| password | String | Yes | User's password |
| phoneNumber | String | No | Optional phone number |

Success Response – 200 OK:
```json
{
  "userId": "550e8400-e29b-41d4-a716-446655440000",
  "email": "john.doe@example.com"
}
```

---

### Update User

Update an existing user's information.

Endpoint:
```
POST /user/updateUser
```

Request Body:
```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "first_name": "John",
  "last_name": "Smith",
  "phoneNumber": "0987654321"
}
```

Request Fields:

| Field | Type | Required | Description |
|------|------|----------|-------------|
| id | UUID | Yes | User ID |
| first_name | String | Yes | Updated first name |
| last_name | String | Yes | Updated last name |
| phoneNumber | String | Yes | Updated phone number |

Success Response – 200 OK:
```
Information has been updated
```

---

## DTOs

RegisterRequest:
```java
public record RegisterRequest(
    String firstName,
    String lastName,
    String email,
    String password,
    Optional<String> phoneNumber
) {}
```

RegisterResponse:
```java
public record RegisterResponse(
    UUID userId,
    String email
) {}
```

UpdateUserRequest:
```java
public record UpdateUserRequest(
    UUID id,
    String first_name,
    String last_name,
    String phoneNumber
) {}
```

---

## Notes

- `phoneNumber` in registration is optional.
- All IDs are UUIDs.
- Consider adding validation and error handling.
