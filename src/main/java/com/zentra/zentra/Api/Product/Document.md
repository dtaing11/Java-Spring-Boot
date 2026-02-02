# Zentra Product API

This module provides REST APIs for creating, updating, and retrieving products within an organization.

Base path:

    /product

---

## Tech Stack

- Java
- Spring Boot
- Spring Web (REST)
- Spring Security (@AuthenticationPrincipal)
- UUID-based IDs

---

## API Endpoints

### Create Product

Create a new product under an organization.  
Authenticated user is used for authorization.

Endpoint:

    POST /product/create

Request Body:

    {
      "name": "Premium Subscription",
      "description": "Monthly premium plan",
      "orgId": "550e8400-e29b-41d4-a716-446655440000"
    }

Request Fields:

| Field | Type | Required | Description |
|------|------|----------|-------------|
| name | String | Yes | Product name |
| description | String (Optional) | No | Optional product description |
| orgId | UUID | Yes | Organization ID |

Success Response – 200 OK:

    {
      "id": "9c12e0d1-3b4f-4f6e-9c91-9e4f2a8d1234",
      "name": "Premium Subscription",
      "description": "Monthly premium plan",
      "orgId": "550e8400-e29b-41d4-a716-446655440000",
      "createdBy": "8c7c5f27-6f95-4f58-8f2d-4bd2b7a2d9a1",
      "createdAt": "2026-02-02T18:35:11.412Z"
    }

---

### Update Product

Update an existing product’s name or description.

Endpoint:

    PUT /product/update

Request Body:

    {
      "pd_id": "9c12e0d1-3b4f-4f6e-9c91-9e4f2a8d1234",
      "name": "Premium Plus Subscription",
      "description": "Updated premium plan"
    }

Request Fields:

| Field | Type | Required | Description |
|------|------|----------|-------------|
| pd_id | UUID | Yes | Product ID |
| name | String (Optional) | No | Updated product name |
| description | String (Optional) | No | Updated product description |

Success Response – 200 OK:

    Product has been updated: Premium Plus Subscription

---

### Find Products by Organization

Retrieve all products belonging to an organization that the user has access to.

Endpoint:

    GET /product/find/{orgId}

Path Parameters:

| Parameter | Type | Required | Description |
|----------|------|----------|-------------|
| orgId | UUID | Yes | Organization ID |

Success Response – 200 OK:

    [
      {
        "id": "9c12e0d1-3b4f-4f6e-9c91-9e4f2a8d1234",
        "name": "Premium Subscription",
        "description": "Monthly premium plan",
        "orgId": "550e8400-e29b-41d4-a716-446655440000"
      }
    ]

---

## DTOs

CreateRequest:

    public record CreateRequest(
        String name,
        Optional<String> description,
        UUID orgId
    ) {}

UpdateRequest:

    public record UpdateRequest(
        Optional<String> name,
        Optional<String> description,
        UUID pd_id
    ) {}

---

## Notes

- Authenticated user is injected using @AuthenticationPrincipal User user
- name and description in updates are optional
- All IDs are UUIDs
- Authorization and ownership checks are enforced in ProductService
