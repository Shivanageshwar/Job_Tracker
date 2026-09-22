# Job & Internship Application Tracker

A backend REST API built with **Java and Spring Boot** to help users manage and track their job and internship applications in one place.

The application provides user authentication, JWT-based authorization, CRUD operations for applications, filtering, and MySQL database integration.

---

## Features

* User registration
* User login with JWT authentication
* Password hashing using BCrypt
* JWT-based authorization
* Create job/internship applications
* View applications
* View an application by ID
* Update applications
* Delete applications
* Filter applications by status
* Filter applications by company
* User-specific application data
* MySQL database integration
* RESTful API architecture
* Exception handling
* Input validation

---

##  Tech Stack

### Backend

* Java
* Spring Boot
* Spring Security
* Spring Data JPA
* Hibernate
* REST APIs

### Database

* MySQL

### Authentication

* JWT (JSON Web Token)
* BCrypt Password Encoding

### Tools

* Maven
* Git
* GitHub
* VS Code
* Thunder Client

---

## Project Architecture

The application follows a layered architecture:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
MySQL Database
```

### Main Layers

**Controller**

* Handles HTTP requests and responses.
* Exposes REST API endpoints.

**Service**

* Contains business logic.
* Handles application and authentication operations.

**Repository**

* Communicates with the database using Spring Data JPA.

**Entity**

* Represents database tables.

**Security**

* Handles JWT authentication and authorization.

**DTO**

* Handles request and response data.

**Exception**

* Handles application errors and validation failures.

---

## Project Structure

```text
src
└── main
    ├── java
    │   └── com.example.demo
    │       │
    │       ├── ApplicationController.java
    │       ├── AuthController.java
    │       ├── DemoApplication.java
    │       │
    │       ├── dto
    │       │   ├── AuthResponse.java
    │       │   ├── LoginRequest.java
    │       │   └── RegisterRequest.java
    │       │
    │       ├── entity
    │       │   ├── JobApplication.java
    │       │   └── User.java
    │       │
    │       ├── exception
    │       │   ├── ApplicationNotFoundException.java
    │       │   └── GlobalExceptionHandler.java
    │       │
    │       ├── repository
    │       │   ├── JobApplicationRepository.java
    │       │   └── UserRepository.java
    │       │
    │       ├── security
    │       │   ├── JwtAuthenticationFilter.java
    │       │   ├── JwtService.java
    │       │   └── SecurityConfig.java
    │       │
    │       └── service
    │           ├── AuthService.java
    │           └── JobApplicationService.java
    │
    └── resources
        └── application.properties
```

---

## Authentication Flow

The application uses JWT-based authentication.

```text
Register
   ↓
User stored in MySQL
   ↓
Login
   ↓
Credentials verified
   ↓
JWT Token generated
   ↓
Client sends token with API requests
   ↓
JWT Filter validates token
   ↓
Protected API accessed
```

Protected endpoints require the following header:

```text
Authorization: Bearer <JWT_TOKEN>
```

---

## 🔗 API Endpoints

### Authentication

| Method | Endpoint         | Description                 | Authentication |
| ------ | ---------------- | --------------------------- | -------------- |
| POST   | `/auth/register` | Register a new user         | No             |
| POST   | `/auth/login`    | Login and receive JWT token | No             |

### Job Applications

| Method | Endpoint                              | Description           | Authentication |
| ------ | ------------------------------------- | --------------------- | -------------- |
| POST   | `/applications`                       | Create an application | Yes            |
| GET    | `/applications`                       | Get applications      | Yes            |
| GET    | `/applications/{id}`                  | Get application by ID | Yes            |
| PUT    | `/applications/{id}`                  | Update application    | Yes            |
| DELETE | `/applications/{id}`                  | Delete application    | Yes            |
| GET    | `/applications/status/{status}`       | Filter by status      | Yes            |
| GET    | `/applications/company/{companyName}` | Filter by company     | Yes            |

---

## Example Registration Request

### POST

```text
/auth/register
```

### Request Body

```json
{
    "name": "Shiva",
    "email": "shiva@example.com",
    "password": "123456"
}
```

---

## Example Login Request

### POST

```text
/auth/login
```

### Request Body

```json
{
    "email": "shiva@example.com",
    "password": "123456"
}
```

The API returns a JWT token that can be used to access protected endpoints.

---

##  Example Job Application

### POST

```text
/applications
```

### Request Body

```json
{
    "companyName": "Example Technologies",
    "jobRole": "Java Developer",
    "applicationDate": "2026-09-22",
    "status": "Applied",
    "location": "Hyderabad",
    "jobType": "Full-Time",
    "applicationUrl": "https://example.com/job",
    "notes": "Applied through company career portal"
}
```

### Authorization

```text
Authorization: Bearer <JWT_TOKEN>
```

---

## Database

The project uses **MySQL** as the relational database.

### Users Table

Stores registered user information.

```text
id
name
email
password
```

Passwords are stored using **BCrypt hashing** rather than plain text.

### Job Applications Table

Stores job and internship application information.

```text
id
companyName
jobRole
applicationDate
status
location
jobType
applicationUrl
notes
user_id
```

---

## Setup & Installation

### 1. Clone the repository

```bash
git clone https://github.com/YOUR_USERNAME/job-internship-tracker.git
```

### 2. Navigate to the project

```bash
cd job-internship-tracker
```

### 3. Configure MySQL

Create a MySQL database:

```sql
CREATE DATABASE job_tracker;
```

Configure the database connection using environment variables or your local configuration.

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/job_tracker
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

spring.jpa.hibernate.ddl-auto=update
```

### 4. Run the application

On Windows:

```bash
./mvnw.cmd spring-boot:run
```

The application runs on:

```text
http://localhost:8081
```

---

## Testing

The REST APIs can be tested using:

* Thunder Client
* Postman

Recommended testing sequence:

```text
1. Register
      ↓
2. Login
      ↓
3. Copy JWT token
      ↓
4. Add Bearer token
      ↓
5. Create application
      ↓
6. View applications
      ↓
7. Update application
      ↓
8. Filter applications
      ↓
9. Delete application
```

---

## Security

The application implements:

* Spring Security
* JWT authentication
* BCrypt password hashing
* Stateless authentication
* Protected REST endpoints
* Authorization using Bearer tokens

Sensitive credentials and JWT secrets should be supplied through environment variables rather than committed to the repository.

---

## Future Improvements

Planned improvements include:

* Application dashboard with statistics
* Search and advanced filtering
* Pagination and sorting
* Email reminders for interviews and application deadlines
* Resume/document management
* Frontend using React
* Deployment to a cloud platform
* Improved validation and API documentation using Swagger/OpenAPI

---

## Learning Outcomes

Through this project, I practiced:

* Java backend development
* Spring Boot application development
* REST API design
* Spring Security
* JWT authentication
* MySQL database integration
* Spring Data JPA
* Hibernate
* Layered architecture
* Exception handling
* API testing
* Git and GitHub

---

##  Author

**Shiva Nageshwar Rao**

B.Tech Computer Science Engineering

Interested in:

* Java Backend Development
* Software Engineering
* Data Structures & Algorithms
* SQL & Database Systems
* Blockchain & Smart Contract Development
