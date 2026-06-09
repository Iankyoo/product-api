# Product API

REST API built with Spring Boot as the consolidation project for **Module 3** of the Java Backend learning roadmap.

Built from scratch without referencing prior code — designed to reinforce and validate Spring Boot fundamentals through independent application.

---

## About

This project simulates a product management system for an e-commerce context. It covers all core concepts introduced in Module 3, applied together in a real-world API structure.

---

## Features

- List products with pagination and sorting
- Search products by name (case-insensitive)
- Create, update and delete products
- Input validation with meaningful error messages
- Global exception handling with standardized error responses
- Structured logging with SLF4J

---

## Tech Stack

- Java 21
- Spring Boot 3.5.14
- Spring Data JPA
- Spring Validation
- PostgreSQL
- Lombok
- Maven

---

## Project Structure

```
src/main/java/com/iankyoo/product_api/
├── controller/
│   └── ProductController.java
├── service/
│   └── ProductService.java
├── repository/
│   └── ProductRepository.java
├── entity/
│   └── Product.java
├── dto/
│   ├── CreateProductRequest.java
│   ├── UpdateProductRequest.java
│   ├── ProductResponse.java
│   └── ErrorResponse.java
└── exception/
    ├── ProductNotFoundException.java
    └── GlobalExceptionHandler.java
```

---

## Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/v1/products` | List all products (paginated) |
| GET | `/api/v1/products/{id}` | Find product by ID |
| GET | `/api/v1/products/search?name=` | Search products by name |
| POST | `/api/v1/products` | Create product |
| PUT | `/api/v1/products/{id}` | Update product |
| DELETE | `/api/v1/products/{id}` | Delete product |

### Pagination parameters

```
?page=0&size=10&sort=name
```

### Create / Update request body

```json
{
    "name": "Notebook Dell XPS",
    "description": "Development laptop",
    "price": 8500.00,
    "category": "Electronics"
}
```

---

## Running Locally

**Requirements:**
- Java 21+
- Maven 3.6+
- PostgreSQL (or Docker)

**Start PostgreSQL with Docker:**

```bash
docker run --name postgres-dev -e POSTGRES_USER=bank_user -e POSTGRES_PASSWORD=bank_pass -e POSTGRES_DB=bank_db -p 5433:5432 -d postgres
```

**Configure `application.properties`:**

```properties
spring.datasource.url=jdbc:postgresql://localhost:5433/bank_db
spring.datasource.username=bank_user
spring.datasource.password=bank_pass
```

**Run:**

```bash
mvn spring-boot:run
```

API available at `http://localhost:8080`

---

## Module 3 Concepts Applied

| Concept | Where |
|---------|-------|
| IoC and Dependency Injection | All layers via constructor injection |
| Spring Data JPA | `ProductRepository` extending `JpaRepository` |
| DTOs | `CreateProductRequest`, `UpdateProductRequest`, `ProductResponse` |
| Bean Validation | `@NotBlank`, `@NotNull`, `@Positive` on request DTOs |
| HTTP Status Codes | `200`, `201`, `204`, `400`, `404`, `500` |
| Global Exception Handling | `GlobalExceptionHandler` with `@ControllerAdvice` |
| Pagination and Filtering | `PageRequest`, query methods |
| Structured Logging | `@Slf4j` on service and controller |

---

## Learning Roadmap

This project is part of a structured Java backend learning plan:

```
✅ Module 1A — Core Java
✅ Module Extra — Ecosystem (Maven, Lombok, DevTools)
✅ Module 1B — Modern Java (Streams, Lambda, Optional)
✅ Module 2 — SQL, JDBC, JPA/Hibernate
✅ Module 3 — Spring Boot Core ← this project
⬜ Module 4 — Security (Spring Security, JWT)
⬜ Module 5 — Testing
⬜ Module 6 — Concurrency
⬜ Module 7 — Architecture and Patterns
⬜ Module 8 — Messaging and Cache
⬜ Module 9 — Deploy and Cloud
```

Full roadmap: [plano-Java](https://github.com/Iankyoo/plano-Java)
