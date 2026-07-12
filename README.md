# Student Course Management System REST API

A RESTful backend application built using **Java**, **JAX-RS (Jersey)**, **JDBC**, **Maven**, and **MySQL** that manages students, courses, and enrollments.

The project follows a layered architecture with dedicated **Resource**, **Service**, **Repository**, **DTO**, and **Mapper** layers, demonstrating clean separation of concerns, REST API design, validation, and exception handling.

---

## Features

- Student Management (CRUD)
- Course Management (CRUD)
- Enrollment Management
- Prevent duplicate enrollments
- Request and Response DTOs
- Dedicated Mapper layer
- Layered architecture
- Input validation
- Custom exception handling
- Relational database design

---

## Tech Stack

- Java
- JAX-RS (Jersey)
- JDBC
- MySQL
- Maven
- Apache Tomcat
- Eclipse IDE
- Git
- GitHub
- Postman

---

## Architecture

The project follows a layered architecture.

```text
Client
   │
   ▼
Request DTO
   │
   ▼
Resource Layer
   │
   ▼
Mapper Layer
   │
   ▼
Service Layer
   │
   ▼
Repository Layer
   │
   ▼
MySQL Database
```

### Layers

- **Resource Layer** – Handles HTTP requests and responses.
- **DTO Layer** – Defines request and response objects exchanged with clients.
- **Mapper Layer** – Converts between DTOs and domain models.
- **Service Layer** – Performs validation, business logic, and exception translation.
- **Repository Layer** – Executes SQL operations using JDBC.
- **Model Layer** – Represents domain entities.

---

## Project Structure

```text
src/main/java
│
└── com.ankur
    ├── config
    ├── dto
    ├── exceptions
    ├── mappers
    ├── models
    ├── repositories
    ├── resources
    ├── services
    └── utils
```

---

## Database Design

The application consists of three relational tables.

### Students

Stores student information.

### Courses

Stores course information.

- Unique course names
- Positive duration constraint
- Non-negative fees constraint

### Enrollments

Represents the many-to-many relationship between students and courses.

- Composite Primary Key
- Foreign Keys
- ON DELETE CASCADE (Student)
- ON DELETE RESTRICT (Course)

---

## REST API Endpoints

### Students

| Method | Endpoint |
|--------|----------|
| POST | `/students` |
| GET | `/students` |
| GET | `/students/{id}` |
| PUT | `/students/{id}` |
| DELETE | `/students/{id}` |

---

### Courses

| Method | Endpoint |
|--------|----------|
| POST | `/courses` |
| GET | `/courses` |
| GET | `/courses/{id}` |
| PUT | `/courses/{id}` |
| DELETE | `/courses/{id}` |

---

### Enrollments

| Method | Endpoint |
|--------|----------|
| POST | `/enrollments` |
| GET | `/enrollments` |
| GET | `/enrollments/student/{studentId}` |
| GET | `/enrollments/course/{courseId}` |
| DELETE | `/enrollments/student/{studentId}/course/{courseId}` |

---

## Exception Handling

The application uses a custom exception hierarchy.

- ValidationException
- DatabaseOperationException
- StudentNotFoundException
- CourseNotFoundException
- EnrollmentNotFoundException
- DuplicateEnrollmentException
- RepositoryException

Each exception is mapped to an appropriate HTTP status code.

---

## HTTP Status Codes

| Status | Description |
|---------|-------------|
| 200 | Success |
| 201 | Resource Created |
| 400 | Validation Failed |
| 404 | Resource Not Found |
| 409 | Duplicate Enrollment |
| 500 | Database / Server Error |

---

## Testing

The API was tested using Postman.

Verified functionality includes:

- Student CRUD operations
- Course CRUD operations
- Enrollment CRUD operations
- Duplicate enrollment prevention
- Input validation
- HTTP status code handling
- Integration between all modules

---

## How to Run

1. Clone the repository.
2. Create the MySQL database.
3. Execute the SQL scripts to create the tables.
4. Update the database credentials in `JDBCUtil`.
5. Build the project using Maven.
6. Deploy the WAR file to Apache Tomcat.
7. Test the endpoints using Postman.

---

## Future Improvements

- Global Exception Handling using `ExceptionMapper`
- Standardized API Response Objects
- Logging (SLF4J / Logback)
- Unit Testing (JUnit + Mockito)
- Integration Testing
- Spring Boot migration
- Spring Data JPA / Hibernate
- JWT Authentication
- Swagger / OpenAPI documentation
- Docker support

---

## Version History

### v1.1.0

- Introduced Request and Response DTOs
- Added dedicated Mapper layer
- Refactored REST resources to use DTOs instead of exposing domain models
- Improved project architecture while preserving existing functionality

### v1.0.0

- Initial RESTful CRUD implementation
- Student, Course, and Enrollment management
- JDBC persistence
- Layered architecture
- Custom exception handling

---

## Author

**Ankur Mishra**