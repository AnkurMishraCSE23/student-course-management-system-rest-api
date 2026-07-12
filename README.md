# Student Course Management System REST API

A RESTful backend application built using **Java**, **JAX-RS (Jersey)**, **JDBC**, and **MySQL** that manages students, courses, and enrollments. The project follows a layered architecture consisting of Resource, Service, and Repository layers, with custom exception handling and relational database design.

---

## Features

### Student Management
- Create a student
- Retrieve all students
- Retrieve a student by ID
- Update student details
- Delete a student

### Course Management
- Create a course
- Retrieve all courses
- Retrieve a course by ID
- Update course details
- Delete a course

### Enrollment Management
- Enroll a student in a course
- Prevent duplicate enrollments
- Retrieve all enrollments
- Retrieve enrollments by student
- Retrieve enrollments by course
- Delete an enrollment

---

## Tech Stack

- Java
- JAX-RS (Jersey)
- JDBC
- MySQL
- Maven
- Apache Tomcat
- Eclipse IDE

---

## Architecture

The project follows a layered architecture.

```
Client
   │
   ▼
Resource Layer
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
- **Service Layer** – Performs validation, business logic, and exception translation.
- **Repository Layer** – Executes SQL operations using JDBC.
- **Model Layer** – Represents domain entities.

---

## Project Structure

```
src/main/java
│
├── com.ankur.config
├── com.ankur.exceptions
├── com.ankur.models
├── com.ankur.repositories
├── com.ankur.resources
├── com.ankur.services
└── com.ankur.utils
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

- DTOs (Data Transfer Objects)
- Global Exception Handling using `ExceptionMapper`
- Standardized API Response Objects
- Logging
- Unit Testing (JUnit)
- Integration Testing
- Spring Boot migration
- Swagger/OpenAPI documentation

---

## Author

**Ankur**