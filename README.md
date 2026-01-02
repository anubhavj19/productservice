# Product Service

A Spring Boot microservice for managing products and categories with multiple data source support.

## Overview

Product Service is a RESTful microservice built with Spring Boot that provides comprehensive product and category management capabilities. It supports multiple backend implementations, allowing seamless switching between a self-managed PostgreSQL database and external FakeStore API integration.

## Features

- **Dual Product Service Implementation**
  - `SelfProductService`: Uses PostgreSQL with JPA/Hibernate for data persistence
  - `FakeStoreProductService`: Integrates with external FakeStore API for demo/testing purposes

- **Core Functionality**
  - CRUD operations for products and categories
  - Paginated product search with sorting capabilities
  - Category management with automatic creation
  - Redis caching for improved performance
  - Service discovery with Netflix Eureka
  - Health monitoring with Spring Boot Actuator

- **Database Support**
  - PostgreSQL for production data storage
  - JPA repositories with custom query methods
  - Flyway migrations for database versioning
  - Category and product relationship management

## Tech Stack

- **Java 17**
- **Spring Boot 3.2.4**
- **Spring Data JPA** - Database interaction and ORM
- **PostgreSQL 42.6.0** - Primary database
- **Redis 3.3.3** - Caching layer
- **Netflix Eureka Client 4.1.4** - Service discovery
- **Lombok** - Boilerplate code reduction
- **Maven** - Build and dependency management
- **Spring Boot Actuator** - Production monitoring

## Architecture

### Key Components

- **Controllers**: RESTful API endpoints for product and category operations
- **Services**: Business logic layer with abstraction for multiple implementations
- **Repositories**: JPA repositories with custom query methods
- **DTOs**: Data transfer objects for API request/response handling
- **Models**: JPA entities for database mapping
- **Advices**: Global exception handling
- **Configuration**: Application-wide configuration and bean definitions

### Project Structure

```
src/main/java/com/example/productservice/
├── ProductServiceApplication.java
├── advices/              # Global exception handlers
├── commons/              # Shared utilities
├── config/               # Spring configuration classes
├── controller/           # REST controllers
├── dto/                  # Data transfer objects
├── exceptions/           # Custom exceptions
├── model/                # JPA entities
├── repository/           # Data access layer
└── service/              # Business logic layer
```

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6+
- PostgreSQL database
- Redis server (optional, for caching)

## API Endpoints

### Products
- `GET /products` - Get all products (paginated)
- `GET /products/{id}` - Get product by ID
- `POST /products` - Create a new product
- `PUT /products/{id}` - Update a product
- `DELETE /products/{id}` - Delete a product

### Categories
- `GET /categories` - Get all categories
- `GET /categories/{id}` - Get category by ID
- `POST /categories` - Create a new category
- `PUT /categories/{id}` - Update a category
- `DELETE /categories/{id}` - Delete a category

## License

This project is part of a learning exercise at Scaler Academy.

