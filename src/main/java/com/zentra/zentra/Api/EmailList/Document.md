# Zentra Email List API

This module provides REST APIs for managing email sign-ups associated with products.

Base path:

    /emaillist

---

## Tech Stack

- Java
- Spring Boot
- Spring Web (REST)
- Spring Security (@AuthenticationPrincipal)
- UUID-based IDs

---

## API Endpoints

### Sign Up to Email List

Sign up an email address to a product’s email list.  
Authenticated user is used for authorization.

Endpoint:

    POST /emaillist/signUp

Request Body:

    {
      "email": "user@example.com",
      "pdId": "550e8400-e29b-41d4-a716-446655440000"
    }

Request Fields:

| Field | Type | Required | Description |
|------|------|----------|-------------|
| email | String | Yes | Email address to subscribe |
| pdId | UUID | Yes | Product ID |

Success Response – 200 OK:

    Sign up successful

---

### Delete Email from List

Remove the authenticated user’s email subscription from a product.

Endpoint:

    DELETE /emaillist/delete

Request Body:

    "550e8400-e29b-41d4-a716-446655440000"

Request Fields:

| Field | Type | Required | Description |
|------|------|----------|-------------|
| pdId | UUID | Yes | Product ID |

Success Response – 200 OK:

    Delete successful from: Product Name

---

### Get Email List by Product

Retrieve all email sign-ups for a specific product that the user has access to.

Endpoint:

    GET /emaillist/{pd_Id}

Path Parameters:

| Parameter | Type | Required | Description |
|----------|------|----------|-------------|
| pd_Id | UUID | Yes | Product ID |

Success Response – 200 OK:

    [
      {
        "id": "9c12e0d1-3b4f-4f6e-9c91-9e4f2a8d1234",
        "email": "user@example.com",
        "pdId": "550e8400-e29b-41d4-a716-446655440000",
        "createdAt": "2026-02-02T19:02:11.412Z"
      }
    ]

If no emails are found:

    404 Not Found

---

## DTOs

SignUpRequest:

    public record SignUpRequest(
        String email,
        UUID pdId
    ) {}

---

## Notes

- Authenticated user is injected using @AuthenticationPrincipal User user
- Email uniqueness and ownership checks are enforced in EmailListService
- All IDs are UUIDs
- Consider adding email validation and standardized error responses
