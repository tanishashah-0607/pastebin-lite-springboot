# Pastebin Lite – Spring Boot Backend

A Pastebin-like backend application built using Spring Boot.

## Features
- Create paste with optional expiry time
- Limit number of views per paste
- Fetch paste by ID
- Auto-delete paste after expiry or view limit
- Proper HTTP status codes (200, 404, 410)
- REST APIs tested via Postman

## Tech Stack
- Java 17
- Spring Boot
- Spring Data JPA
- MySQL (local)
- Thymeleaf (basic UI)
- Maven

## API Endpoints

### Create Paste
POST `/api/pastes`

### Fetch Paste
GET `/api/pastes/{id}`

Returns:
- `200 OK` – paste found
- `410 Gone` – expired or view limit exceeded
- `404 Not Found` – invalid ID

## How to Run Locally
1. Clone the repository
2. Configure database in `application.yml`
3. Run `PastebinApplication`
4. Server runs on `http://localhost:8080`

## Author
Tanisha Shah
