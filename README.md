Author Management API
REST API for managing authors, works, and administrators, developed with Java and Spring Boot.

The project was built to practice and demonstrate backend development concepts such as REST APIs, layered architecture, data persistence, validation, exception handling, JWT authentication, and API documentation with OpenAPI.

🚀 Technologies

Java 21
Spring Boot 4.1.1
Spring Web MVC
Spring Data JPA
Spring Security
Spring Validation
Springdoc OpenAPI
Swagger UI
H2 Database
MapStruct
Lombok
Auth0 Java JWT
Gradle
📋 Features

Authors

Create authors
Find an author by ID
List authors with pagination
Filter authors by name
Update authors
Delete authors
Prevent deleting authors with associated works
Administrators

Create administrators
Find administrators by ID
List administrators with pagination
Update administrators
Delete administrators
Activate administrators
Deactivate administrators
Authentication

Sign in using email and password
JWT authentication
Stateless authentication
Protected API endpoints
API Documentation

OpenAPI documentation
Swagger UI
JWT authentication support for testing protected endpoints
🏗️ Project Architecture

The application follows a layered architecture, separating responsibilities between controllers, services, repositories, models, DTOs, and mappers.

src/main/java/br/com/apiserver
│
├── author
│   ├── controller
│   │   ├── dto
│   │   └── mapper
│   ├── exception
│   ├── model
│   ├── repository
│   └── service
│
├── authentication
│   ├── controller
│   │   ├── dto
│   │   └── mapper
│   ├── model
│   ├── repository
│   └── service
│
└── security
    ├── auth
    ├── configuration
    └── filter
The general request flow is:

HTTP Request
     ↓
Controller
     ↓
Service
     ↓
Repository
     ↓
Database
DTOs are used to define the API contract, while MapStruct is responsible for mapping between DTOs and domain models.

🔐 Authentication

Authentication is implemented using JWT and Spring Security.

To authenticate, send the user's credentials to:

POST /api/v1/auth/sign-in
Example request:

{
  "email": "admin@example.com",
  "password": "password"
}
After successful authentication, the API returns an access token.

Protected endpoints require the following header:

Authorization: Bearer <access-token>
📚 API Endpoints

Authentication

Method	Endpoint	Description
POST	/api/v1/auth/sign-in	Authenticate a user
Authors

Method	Endpoint	Description
POST	/api/v1/authors	Create an author
GET	/api/v1/authors/{id}	Find an author by ID
GET	/api/v1/authors	List authors
PUT	/api/v1/authors/{id}	Update an author
DELETE	/api/v1/authors/{id}	Delete an author
The author deletion operation is prevented when the author has associated works.

In this case, the API returns:

409 Conflict
Administrators

Method	Endpoint	Description
POST	/api/v1/administrators	Create an administrator
GET	/api/v1/administrators/{id}	Find an administrator by ID
GET	/api/v1/administrators	List administrators
PUT	/api/v1/administrators/{id}	Update an administrator
DELETE	/api/v1/administrators/{id}	Delete an administrator
PATCH	/api/v1/administrators/{id}/activate	Activate an administrator
PATCH	/api/v1/administrators/{id}/deactivate	Deactivate an administrator
📖 Swagger UI

After starting the application, the interactive API documentation is available at:

http://localhost:8080/swagger-ui/index.html
The OpenAPI specification is available at:

http://localhost:8080/v3/api-docs
Swagger UI can be used to test the API endpoints and, when authentication is configured, send JWT tokens to protected endpoints.

⚙️ Requirements

Before running the project, make sure you have:

Java 21
Git
Gradle or the Gradle Wrapper included in the project
▶️ Running the Application

Clone the repository:

git clone https://github.com/Lucas-Lor3nzoni/Author-Management.git
Navigate to the API:

cd Author-Management/api-server
Run the application with Gradle:

Linux / macOS

./gradlew bootRun
Windows

gradlew.bat bootRun
The application will be available at:

http://localhost:8080
🗄️ Database

The project uses an H2 database for development.

The H2 console can be accessed at:

http://localhost:8080/h2-console
Database configuration is defined in the application's configuration files.

🧪 Running Tests

To execute the test suite:

Linux / macOS

./gradlew test
Windows

gradlew.bat test
📦 Building the Application

To build the project:

./gradlew build
The generated JAR file will be available in:

build/libs/
🛡️ Error Handling

The API uses centralized exception handling to provide consistent error responses.

Some of the HTTP status codes used by the API are:

Status	Description
200	Request successfully processed
201	Resource successfully created
204	Request successfully processed without response body
400	Invalid request or validation error
401	Unauthorized
404	Resource not found
409	Business rule conflict
500	Unexpected server error
Example error response:

{
  "message": "An unexpected error occurred",
  "statusCode": 500,
  "timestamp": "2026-10-07T22:16:33.005309Z",
  "errors": null
}
🔎 Pagination

Collection endpoints support pagination using Spring Data's Pageable.

Example:

GET /api/v1/authors?page=0&size=10
Authors can also be filtered by name:

GET /api/v1/authors?name=Machado&page=0&size=10
🎯 Project Goals

This project was developed to practice and demonstrate:

REST API development
Java and Spring Boot
Layered architecture
Dependency injection
Spring Data JPA
DTO pattern
MapStruct
Bean Validation
Exception handling
Spring Security
JWT authentication
Pagination
Business rule validation
OpenAPI and Swagger documentation
👨‍💻 Author

Lucas Lorenzoni

GitHub:

https://github.com/Lucas-Lor3nzoni
Built with Java and Spring Boot. :::