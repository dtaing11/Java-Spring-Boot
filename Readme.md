# Zentra API – Raw JSON Bodies

This document lists **raw JSON request and response bodies** for each API endpoint  
and clearly specifies **which endpoints require the session cookie**.

---

## SESSION AUTHENTICATION

Cookie name:
```
session
```

Issued by:
```
POST /auth/login
```

Used for:
- All protected endpoints

Cookie header example:
```
Cookie: session=550e8400-e29b-41d4-a716-446655440000
```

---

## USER API

Base path:
```
/user
```

---

### Register User

Endpoint:
```
POST /user/register
```

Authentication Required:
```
NO
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

Response Body (200 OK):
```json
{
  "userId": "550e8400-e29b-41d4-a716-446655440000",
  "email": "john.doe@example.com"
}
```

---

### Update User

Endpoint:
```
POST /user/updateUser
```

Authentication Required:
```
YES (session cookie)
```

Request Headers:
```
Cookie: session=<UUID>
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

Response Body (200 OK):
```json
"Information has been updated"
```

---

## AUTH API

Base path:
```
/auth
```

---

### Login

Endpoint:
```
POST /auth/login
```

Authentication Required:
```
NO
```

Request Body:
```json
{
  "email": "john.doe@example.com",
  "password": "StrongPassword123"
}
```

Response Headers (200 OK):
```
Set-Cookie: session=550e8400-e29b-41d4-a716-446655440000; HttpOnly; Path=/; Max-Age=2592000; SameSite=Lax
```

Response Body:
```json
"Cookie has been set"
```

---

## ORGS API

Base path:
```
/orgs
```

---

### Create Organization

Endpoint:
```
POST /orgs/create
```

Authentication Required:
```
YES (session cookie)
```

Request Headers:
```
Cookie: session=<UUID>
```

Request Body:
```json
{
  "name": "Zentra Inc",
  "description": "Main organization"
}
```

Response Body (200 OK):
```json
{
  "orgId": "660e8400-e29b-41d4-a716-446655440000",
  "name": "Zentra Inc",
  "statusUpdate": "You've created an Orgs"
}
```

---

### Update Organization

Endpoint:
```
POST /orgs/update
```

Authentication Required:
```
YES (session cookie)
```

Request Headers:
```
Cookie: session=<UUID>
```

Request Body:
```json
{
  "orgId": "660e8400-e29b-41d4-a716-446655440000",
  "name": "Zentra Updated",
  "description": "Updated description"
}
```

Response Body (200 OK):
```json
"Updated Orgs"
```

---

## ORGS ROLES API

Base path:
```
/orgs/roles
```

---

### Update Organization Role

Endpoint:
```
PUT /orgs/roles/update
```

Authentication Required:
```
YES (session cookie)
```

Request Headers:
```
Cookie: session=<UUID>
```

Request Body:
```json
{
  "orgRoles": "ADMIN",
  "targetID": "550e8400-e29b-41d4-a716-446655440000",
  "orgID": "660e8400-e29b-41d4-a716-446655440000"
}
```

Response Body (200 OK):
```json
"Updated Roles"
```

---

## AUTH RULE SUMMARY

Public endpoints (no cookie required):
```
POST /user/register
POST /auth/login
```

Protected endpoints (session cookie required):
```
POST /user/updateUser
POST /orgs/create
POST /orgs/update
PUT  /orgs/roles/update
```

---

## Notes

- All UUID values are examples
- Missing or invalid session cookie will result in authentication failure
- Session auth is enforced by `SessionAuthFilter`
- Cookies should use `Secure=true` in production
