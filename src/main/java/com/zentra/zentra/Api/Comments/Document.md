# Zentra Comments API

This module provides REST APIs for creating, updating, and deleting comments on posts.

Base path:

    /comments

---

## Tech Stack

- Java
- Spring Boot
- Spring Web (REST)
- Spring Security (@AuthenticationPrincipal)
- UUID-based IDs

---

## API Endpoints

### Create Comment

Create a new comment on a post.  
Authenticated user is used as the comment author.

Endpoint:

    POST /comments/create

Request Body:

    {
      "comments": "This looks great!",
      "postId": "550e8400-e29b-41d4-a716-446655440000"
    }

Request Fields:

| Field | Type | Required | Description |
|------|------|----------|-------------|
| comments | String | Yes | Comment content |
| postId | UUID | Yes | Post ID |

Success Response – 200 OK:

    Comment Post Successfully

---

### Update Comment

Update an existing comment on a post.

Endpoint:

    PUT /comments/update

Request Body:

    {
      "commentId": "9c12e0d1-3b4f-4f6e-9c91-9e4f2a8d1234",
      "comment": "Updated comment text",
      "postId": "550e8400-e29b-41d4-a716-446655440000"
    }

Request Fields:

| Field | Type | Required | Description |
|------|------|----------|-------------|
| commentId | UUID | Yes | Comment ID |
| comment | String | Yes | Updated comment content |
| postId | UUID | Yes | Post ID |

Success Response – 200 OK:

    Comment Update Successfully

---

### Delete Comment

Delete a comment by ID.

Endpoint:

    DELETE /comments/delete

Request Body:

    "9c12e0d1-3b4f-4f6e-9c91-9e4f2a8d1234"

Request Fields:

| Field | Type | Required | Description |
|------|------|----------|-------------|
| commentId | UUID | Yes | Comment ID |

Success Response – 200 OK:

    Comment Delete Successfully

---

## DTOs

CreateRequest:

    public record CreateRequest(
        String comments,
        UUID postId
    ) {}

UpdateRequest:

    public record UpdateRequest(
        UUID commentId,
        String comment,
        UUID postId
    ) {}

---

## Notes

- Authenticated user is injected using @AuthenticationPrincipal User user
- Authorization and ownership checks are enforced in CommentsService
- All IDs are UUIDs
- Consider adding validation (e.g., comment length) and standardized error responses
