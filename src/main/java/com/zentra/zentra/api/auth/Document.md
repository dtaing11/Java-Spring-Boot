# Zentra Auth API

This module provides authentication APIs for user login and session creation.

Base path:
```
/auth
```

---

## Tech Stack

- Java
- Spring Boot
- Spring Web (REST)
- Spring Security
- HTTP Cookies for session management

---

## Authentication Flow

- User logs in with email and password
- A user session is created
- A session cookie is set in the response
- Client uses the cookie for authenticated requests

---

## API Endpoints

### Login

Authenticate a user and create a session.

Endpoint:
```
POST /auth/login
```

Authentication:
- Not required

Request Body:
```json
{
  "email": "john.doe@example.com",
  "password": "StrongPassword123"
}
```

Request Fields:

| Field | Type | Required | Description |
|------|------|----------|-------------|
| email | String | Yes | User email |
| password | String | Yes | User password |

---

Success Response – 200 OK:

Headers:
```
Set-Cookie: session=<UUID>; HttpOnly; Path=/; Max-Age=2592000; SameSite=Lax
```

Body:
```
Cookie has been set
```

---

## Session Cookie Details

| Attribute | Value |
|---------|-------|
| Name | session |
| Type | UUID |
| HttpOnly | true |
| Secure | false (local development only) |
| SameSite | Lax |
| Path | / |
| Max-Age | 30 days |

---

## IP Address Handling

The client IP address is retrieved using:
```java
IpAddressRetrieve.getClientInetAddress(HttpServletRequest)
```

The IP address is passed to the authentication service and associated with the user session.

---

## DTOs

AuthLoginRequest:
```java
public record AuthLoginRequest(
    String email,
    String password
) {}
```

---

## Notes

- Session-based authentication using HTTP-only cookies
- `secure=false` should be set to `true` in production (HTTPS)
- Consider returning structured JSON instead of plain text
- Consider adding rate limiting and login attempt protection
