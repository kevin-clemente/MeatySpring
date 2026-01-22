# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

MeatySpring is a Spring Boot REST API application for managing recipes, ingredients, and ingredient types. The application uses MySQL as the database and follows a standard three-tier architecture (Controller-Service-Repository).

## Technology Stack

- Java 17
- Spring Boot 3.1.4
- Spring Data JPA
- MySQL 8.0
- Maven
- Docker & Docker Compose

## Development Commands

### Build & Run

```bash
# Build the project
./mvnw clean install

# Run the application locally (requires MySQL running on localhost:3306)
./mvnw spring-boot:run

# Run with Docker Compose (includes MySQL)
docker-compose up --build

# Stop Docker services
docker-compose down
```

### Testing

```bash
# Run all tests
./mvnw test

# Run a specific test class
./mvnw test -Dtest=MeatyApplicationTests

# Run a specific test method
./mvnw test -Dtest=MeatyApplicationTests#contextLoads
```

### Database

The application connects to MySQL on port 3306. Database credentials are in:
- `.env` file for Docker
- `src/main/resources/application.properties` for local development

Default database: `meaty`
Default user: `root1`

**Important**: The database schema is recreated on every application startup (`spring.jpa.hibernate.ddl-auto=create-drop`), and sample data is loaded from `src/main/resources/recipes_data.json` via the `RecipeDataLoaderService`.

## Architecture

### Package Structure

```
com.kvanDev.Meaty/
├── api/           # REST Controllers (endpoints)
├── service/       # Business logic layer
├── dao/           # Repository interfaces (data access)
├── model/         # JPA Entity classes
└── meatyConfiguration.java  # Startup configuration (triggers data loading)
```

### Three-Tier Architecture Pattern

The codebase follows a strict layering:

1. **Controller Layer** (`api/`): Handles HTTP requests/responses
   - Uses `@Controller` with `@ResponseBody`, `@RequestMapping`, `@GetMapping`, `@PostMapping`
   - Constructor-based dependency injection

2. **Service Layer** (`service/`): Contains business logic
   - `RecipeDataLoaderService` handles JSON-based data seeding on startup

3. **Repository Layer** (`dao/`): Database access via Spring Data JPA
   - Extends `JpaRepository` for CRUD operations

### Entity Relationships

- **Recipe** → **RecipesIngredients** (one-to-many): Recipes have multiple ingredient entries with quantities
- **RecipesIngredients** → **Ingredient** (many-to-one): Join entity linking recipes to ingredients
- **Ingredient** → **Type** (many-to-one): Each ingredient has one type category

The `RecipesIngredients` entity serves as a join table with an additional `quantity` field.

## API Endpoints

| Method | Path | Description |
|--------|------|-------------|
| GET | `/meaty/getAllRecipes` | Returns all recipes with ingredients |
| POST | `/meaty/createRecipe` | Creates a recipe (requires `userId` header) |
| GET | `/type/all` | Returns all ingredient types |
| GET | `/ingredientes/all` | Returns all ingredients |
| POST | `/ingredientes/create` | Creates an ingredient |

## Development Notes

- The application uses `@WebMvcTest` for controller testing with `MockMvc`
- Entity fields use camelCase (e.g., `recipeName`, `ingredientId`)
- JSON serialization uses `@JsonBackReference` to prevent circular references
- The application runs on port 8080 by default
