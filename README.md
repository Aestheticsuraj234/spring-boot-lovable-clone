# Lovable Clone (Spring Boot)

Backend API for a Lovable-style AI app builder. Manages users, projects, project files, team members, subscriptions, and usage limits.

Built as part of the **Spring Boot 0 To 100** course.

## Tech Stack

- Java 21
- Spring Boot 4.1
- Spring Data JPA
- PostgreSQL
- Lombok
- MapStruct

## Prerequisites

- JDK 21
- Maven 3.9+
- PostgreSQL running locally

## Getting Started

### 1. Database

Create a PostgreSQL database (default config uses the `postgres` database on port `5432`):

```sql
CREATE DATABASE postgres;
```

Update credentials in `src/main/resources/application.yaml` if needed.

### 2. Run the application

```bash
./mvnw spring-boot:run
```

On Windows:

```bash
mvnw.cmd spring-boot:run
```

The server starts on `http://localhost:8080`.

### 3. Run tests

```bash
./mvnw test
```

## Configuration

| Property | Default |
|----------|---------|
| Database URL | `jdbc:postgresql://localhost:5432/postgres` |
| Username | `postgres` |
| Password | `1234567890` |
| JPA DDL | `update` |
| SQL logging | enabled |

Schema is managed automatically via Hibernate (`ddl-auto: update`).

## API Overview

All endpoints currently use a hardcoded `userId = 1L` until Spring Security is added.

### Auth — `/api/auth`

| Method | Path | Description |
|--------|------|-------------|
| POST | `/signup` | Register a new user |
| POST | `/login` | Log in |
| GET | `/me` | Get current user profile |

### Projects — `/api/projects`

| Method | Path | Description |
|--------|------|-------------|
| GET | `/` | List current user's projects |
| GET | `/{id}` | Get project by ID |
| POST | `/` | Create a project |
| PATCH | `/{id}` | Update a project |
| DELETE | `/{id}` | Soft-delete a project |

### Files — `/api/projects/{projectId}/files`

| Method | Path | Description |
|--------|------|-------------|
| GET | `/` | Get project file tree |
| GET | `/{*path}` | Get file content by path |

### Members — `/api/projects/{projectId}/members`

| Method | Path | Description |
|--------|------|-------------|
| GET | `/` | List project members |
| POST | `/` | Invite a member |
| PATCH | `/{memberId}` | Update member role |
| DELETE | `/{memberId}` | Remove a member |

### Billing & Plans

| Method | Path | Description |
|--------|------|-------------|
| GET | `/api/plans` | List active subscription plans |
| GET | `/api/me/subscription` | Get current subscription |
| POST | `/api/stripe/checkout` | Create Stripe checkout session |
| POST | `/api/stripe/portal` | Open Stripe customer portal |

### Usage — `/api/usage`

| Method | Path | Description |
|--------|------|-------------|
| GET | `/today` | Get today's usage |
| GET | `/limits` | Get plan limits for current user |

## Project Structure

```
src/main/java/com/suraj/projects/lovable_clone/
├── controller/     # REST endpoints
├── dto/            # Request/response objects
├── entity/         # JPA entities
├── enums/          # Domain enums
├── mapper/         # MapStruct mappers
├── repository/     # Spring Data repositories
└── service/        # Business logic (interfaces + impl/)
```

## License

MIT (or your preferred license)
