# Spring Boot Secure Note-Taking API

A RESTful API for a multi-user, secure note-taking application. This project is a comprehensive demonstration of building a secure backend using **Spring Boot** and **Spring Security** with **JSON Web Token (JWT)** for authentication and authorization.

## Technologies Used

*   Java 17
*   Spring Boot 3.x
*   Spring Security 6.x
*   Spring Data JPA / Hibernate
*   PostgreSQL
*   JWT (JSON Web Tokens) for stateless authentication
*   Maven
*   Lombok

## Core Features

*   **Secure User Registration:** Passwords are securely hashed using BCrypt before being stored.
*   **Token-Based Authentication:** A stateless login system that provides a JWT upon successful authentication.
*   **Authorization:** Endpoints are protected, requiring a valid JWT for access.
*   **Data Isolation:** The business logic ensures that a logged-in user can only create, view, and manage their *own* notes.

## Getting Started

To get a local copy up and running, follow these steps.

### Prerequisites

*   JDK 17 or higher
*   Maven 3.2+
*   A running instance of PostgreSQL

### Installation and Setup

1.  **Clone the repository**
    ```bash
    git clone https://github.com/your-github-username/secure-notes-api.git
    ```
2.  **Navigate to the project directory**
    ```bash
    cd secure-notes-api
    ```
3.  **Create the database**
    *   In your PostgreSQL client, create a new, empty database named `notes_db`.

4.  **Create the `application.properties` file**
    *   Navigate to `src/main/resources/`.
    *   Create a new file named `application.properties`.
    *   Add the following content, replacing `your_db_password` and choosing a long, random string for `your_jwt_secret`.
    ```properties
    server.port=8092

    spring.datasource.url=jdbc:postgresql://localhost:5432/notes_db
    spring.datasource.username=postgres
    spring.datasource.password=your_db_password

    spring.jpa.hibernate.dd-auto=update

    # JWT Secret Key
    jwt.secret=your_jwt_secret
    ```

### Running the Application

*   Run the `SecureNotesApiApplication.java` file from your IDE.

## API Endpoints

### Authentication (Public)

*   `POST /api/auth/register` - Creates a new user.
    *   Body: `{ "username": "string", "password": "string" }`
*   `POST /api/auth/login` - Authenticates a user and returns a JWT.
    *   Body: `{ "username": "string", "password": "string" }`
    *   Response: `{ "token": "jwt_string" }`

### Notes (Protected - Requires Bearer Token)

*   `GET /api/notes` - Retrieves a list of all notes for the authenticated user.
*   `POST /api/notes` - Creates a new note for the authenticated user.
    *   Body: `{ "title": "string", "content": "string" }`