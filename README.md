# DevTalk - Social Media for Developers 

A robust, production-ready backend for a developer-centric social media platform, built with **Java 17** and **Spring Boot 3**. This project demonstrates advanced Database interactions using **PostgreSQL** and **Spring Data JPA**.

##  Tech Stack
*   **Java 21**
*   **Spring Boot 4.1** (Web, Data JPA)
*   **PostgreSQL** (Advanced relational DB with JSONB support)
*   **Lombok** (Boilerplate reduction)
*   **Maven**

##  Key Architecture & Features

This project focuses on **Best Practices** and solving common backend challenges:

### 1. Database Modeling & Relationships
*   **Complex Relations:**
    *   `Many-to-Many` between **Posts** and **Topics** (using a specific Join Table).
    *   `One-to-Many` / `Many-to-One` between Users, Posts, and Comments.
*   **Bidirectional Mapping Safety:** Used `@ToString.Exclude` and specific DTOs (Data Transfer Objects) to prevent `StackOverflowError` and Circular References.
*   **Database-Level Cascading:** Implemented `@OnDelete(action = OnDeleteAction.CASCADE)` on Comments to ensure efficient cleanup at the database level when a Post is deleted.

### 2. Advanced JPA & Performance Optimization
*   **Solving N+1 Problem:** 
    *   Used **`@EntityGraph`** in `PostRepository` to eagerly fetch comments and topics in a single query when monitoring user posts.
*   **PostgreSQL JSONB Support:**
    *   Mapped User `settings` to a Flexible `Map<String, Object>` using Hibernate 6's `@JdbcTypeCode(SqlTypes.JSON)`.
    *   Implemented **Native Queries** to efficiently search for users based on specific JSON criteria (e.g., retrieving all users with `"theme": "dark"`).

### 3. Business Logic & Atomicity
*   **Transactional Integrity:** 
    *   The `PostService.createPost` method utilizes `@Transactional` to ensure that creating a post and updating the user's `postCount` (stats) happen atomically. If one fails, the entire operation is rolled back.

##  Getting Started

### Prerequisites
1.  **JDK 25** or higher installed.
2.  **PostgreSQL** installed and running.
3.  Create a database named `devtalk_db`:
    ```sql
    CREATE DATABASE devtalk_db;
    ```

### Running the Application
1.  Navigate to the project directory.
2.  Update `src/main/resources/application.properties` if your DB credentials differ from:
    ```properties
    spring.datasource.username= your username
    spring.datasource.password= your password
    ```
3.  Run using Maven:
    ```bash
    mvn spring-boot:run
    ```

## 📡 API Endpoints (Quick Guide)

### 1. Users
**Create User (with JSONB settings):**
*   `POST /api/users`
*   **Body:**
    ```json
    {
      "username": "Kareem Emad",
      "email": "KareemEmad2026@gmail.com",
      "settings": {
        "theme": "dark",
        "notifications": true,
        "language": "ar"
      }
    }
    ```

**Search by JSON Setting:**
*   `GET /api/users/search?theme=dark`

### 2. Posts
**Create Post (Atomic Transaction):**
*   `POST /api/posts`
*   **Body:**
    ```json
    {
      "userId": 1,
      "content": "Learning Spring Boot Advanced Topics!"
    }
    ```

**Get User Posts (Optimized Fetch):**
*   `GET /api/posts/user/1`
    *   *Note: Check your server logs to verify Hibernate executes a single JOIN query!*
