# Zentra Posts API

This module provides REST APIs for creating, deleting, retrieving, and searching posts.

Base path:

    /post

---

## Tech Stack

- Java
- Spring Boot
- Spring Web (REST)
- Spring Security (@AuthenticationPrincipal)
- UUID-based IDs

---

## API Endpoints

### Create Post

Create a new post under an organization.  
Authenticated user is used as the post author.

Endpoint:

    POST /post/create

Request Body:

    {
      "title": "Road closure on Highland",
      "description": "Expect delays near campus until Friday.",
      "orgId": "550e8400-e29b-41d4-a716-446655440000"
    }

Request Fields:

| Field | Type | Required | Description |
|------|------|----------|-------------|
| title | String | Yes | Post title |
| description | String (Optional) | No | Optional post description |
| orgId | UUID | Yes | Organization ID where the post belongs |

Success Response – 200 OK:

    {
      "id": "c0a8012e-7b1b-4e8a-8c06-7c2b7f4f2c1e",
      "title": "Road closure on Highland",
      "description": "Expect delays near campus until Friday.",
      "orgId": "550e8400-e29b-41d4-a716-446655440000",
      "userId": "8c7c5f27-6f95-4f58-8f2d-4bd2b7a2d9a1",
      "createdAt": "2026-02-02T18:20:11.412Z"
    }

---

### Delete Post

Delete a post by ID.  
Authenticated user is required (authorization enforced in service layer).

Endpoint:

    DELETE /post/delete/{postId}

Path Parameters:

| Parameter | Type | Required | Description |
|----------|------|----------|-------------|
| postId | UUID | Yes | Post ID to delete |

Success Response – 200 OK:

    {}

---

### Find All Posts by Organization

Get all posts under a specific organization.

Endpoint:

    GET /post/find/{orgId}

Path Parameters:

| Parameter | Type | Required | Description |
|----------|------|----------|-------------|
| orgId | UUID | Yes | Organization ID |

Success Response – 200 OK:

    [
      {
        "id": "c0a8012e-7b1b-4e8a-8c06-7c2b7f4f2c1e",
        "title": "Road closure on Highland",
        "description": "Expect delays near campus until Friday.",
        "orgId": "550e8400-e29b-41d4-a716-446655440000",
        "userId": "8c7c5f27-6f95-4f58-8f2d-4bd2b7a2d9a1",
        "createdAt": "2026-02-02T18:20:11.412Z"
      }
    ]

---

### Find All Posts by User and Organization

Get all posts created by a user within a specific organization.

Endpoint:

    GET /post/find/{orgId}/{userId}

Path Parameters:

| Parameter | Type | Required | Description |
|----------|------|----------|-------------|
| orgId | UUID | Yes | Organization ID |
| userId | UUID | Yes | User ID |

Success Response – 200 OK:

    [
      {
        "id": "c0a8012e-7b1b-4e8a-8c06-7c2b7f4f2c1e",
        "title": "Road closure on Highland",
        "description": "Expect delays near campus until Friday.",
        "orgId": "550e8400-e29b-41d4-a716-446655440000",
        "userId": "8c7c5f27-6f95-4f58-8f2d-4bd2b7a2d9a1",
        "createdAt": "2026-02-02T18:20:11.412Z"
      }
    ]

---

### Find Post by ID

Get a single post by ID.

Endpoint:

    GET /post/find/id/{id}

Path Parameters:

| Parameter | Type | Required | Description |
|----------|------|----------|-------------|
| id | UUID | Yes | Post ID |

Success Response – 200 OK:

    {
      "id": "c0a8012e-7b1b-4e8a-8c06-7c2b7f4f2c1e",
      "title": "Road closure on Highland",
      "description": "Expect delays near campus until Friday.",
      "orgId": "550e8400-e29b-41d4-a716-446655440000",
      "userId": "8c7c5f27-6f95-4f58-8f2d-4bd2b7a2d9a1",
      "createdAt": "2026-02-02T18:20:11.412Z"
    }

---

### Search Posts

Search posts using a query string.  
Returns lightweight results (NameUUID) for fast search/autocomplete.

Endpoint:

    GET /post/search?search={search}

Query Parameters:

| Parameter | Type | Required | Description |
|----------|------|----------|-------------|
| search | String | Yes | Search keyword |

Example:

    GET /post/search?search=road

Success Response – 200 OK:

    [
      {
        "name": "Road closure on Highland",
        "id": "c0a8012e-7b1b-4e8a-8c06-7c2b7f4f2c1e"
      },
      {
        "name": "Road work near campus",
        "id": "4bb5cc5a-2c2a-4f69-9b23-7f1b821f9fb3"
      }
    ]

---

## DTOs

CreateRequest:

    public record CreateRequest(
        String title,
        Optional<String> description,
        UUID orgId
    ) {}

---

## Notes

- Authenticated user is injected using @AuthenticationPrincipal User user
- description is optional via Optional<String>
- All IDs are UUIDs
- Consider standardizing error responses (401 / 403 / 404) and adding validation
