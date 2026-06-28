# Task Tracker - Full Stack Mini Application

## Project Overview

Task Tracker is a full-stack task management application built using Spring Boot, React, and MySQL. It allows users to manage tasks efficiently with complete CRUD functionality, filtering, sorting, pagination, and project-task relationships.

---

## Tech Stack

### Backend

* Java 21
* Spring Boot
* Spring Data JPA
* Hibernate
* MySQL
* Maven
* Swagger (OpenAPI)
* JUnit 5

### Frontend

* React (Vite)
* Axios
* Bootstrap

### Database

* MySQL

---

## Features

### Backend

* RESTful CRUD APIs
* Task-Project relationship (Foreign Key)
* Server-side validation
* Global exception handling
* Filtering by Status
* Filtering by Priority
* Sorting by Due Date
* Pagination
* Swagger API Documentation
* JUnit Tests

### Frontend

* View Tasks
* Create Task
* Edit Task
* Delete Task
* Filter by Status
* Filter by Priority
* Sort by Due Date
* Pagination

---

## Database Schema

### Projects

| Column      | Type     |
| ----------- | -------- |
| id          | BIGINT   |
| name        | VARCHAR  |
| description | VARCHAR  |
| created_at  | DATETIME |

### Tasks

| Column      | Type                 |
| ----------- | -------------------- |
| id          | BIGINT               |
| title       | VARCHAR              |
| description | VARCHAR              |
| status      | ENUM                 |
| priority    | ENUM                 |
| due_date    | DATE                 |
| created_at  | DATETIME             |
| updated_at  | DATETIME             |
| project_id  | BIGINT (Foreign Key) |

---

## API Endpoints

### Project APIs

| Method | Endpoint           |
| ------ | ------------------ |
| POST   | /api/projects      |
| GET    | /api/projects      |
| GET    | /api/projects/{id} |

### Task APIs

| Method | Endpoint        |
| ------ | --------------- |
| POST   | /api/tasks      |
| GET    | /api/tasks      |
| GET    | /api/tasks/{id} |
| PUT    | /api/tasks/{id} |
| DELETE | /api/tasks/{id} |

---

## Backend Setup

```bash
cd backend
./mvnw spring-boot:run
```

Backend runs on:

```
http://localhost:8080
```

Swagger:

```
http://localhost:8080/swagger-ui/index.html
```

---

## Frontend Setup

```bash
cd frontend
npm install
npm run dev
```

Frontend runs on:

```
http://localhost:5173
```

---

## Database

Create a MySQL database:

```sql
CREATE DATABASE task_tracker;
```

Update the MySQL username and password in:

```
backend/src/main/resources/application.properties
```

---

## Design Decisions

* Layered Architecture (Controller → Service → Repository)
* DTO pattern for request and response objects
* Global exception handling
* JPA relationships for Project and Task
* Axios service layer for API communication
* Bootstrap for responsive UI
* Server-side filtering, sorting, and pagination

---

## Future Improvements

* JWT Authentication
* Role-based Authorization
* Docker Compose
* GitHub Actions CI/CD
* Search by Task Title
* File Attachments
* Notifications
* Optimistic Locking

---

## AI Assistant Usage

ChatGPT was used as a development assistant for:

* Architecture discussions
* Debugging backend and frontend issues
* Code review and improvement suggestions
* README preparation

All implementation, testing, debugging, and understanding of the code were performed and verified before submission.

---

## Author

**Dheenadhayalan S**

GitHub:
https://github.com/DHEENADHAYALAN07
