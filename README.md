# MangaStore API

A secure and robust backend service that powers a full-featured online manga retail platform, complete with user authentication, product management, and order processing.

## 🏗️ Architecture

The project follows Clean Architecture principles with the following layers:

- **Domain**: Business entities (User, Student, Report, Subject, Message)
- **Application**: Use cases, services, and ports (in/out)
- **Infrastructure**: Adapters, controllers, and configuration
- **Web**: REST controllers and security configuration

## 🚀 Technologies Used

- **Spring Boot 3.5.6**: Main framework
- **Spring Security**: Authentication and authorization with JWT
- **Spring Data JPA**: Data persistence
- **H2 Database**: Embedded database for development
- **MapStruct**: Entity-to-DTO mapping
- **Lombok**: Reduces boilerplate code
- **JUnit 5 + Mockito**: Unit testing
- **JaCoCo**: Code coverage
- **Swagger/OpenAPI**: API documentation
- **Maven**: Dependency management

## 📋 Requirements

- Java 21 o superior
- Maven 3.8.5 o superior


## 🏗️ Estructura del Proyecto

```
src/
├── main/
│   ├── java/com/spring/boot/project/ms/manga/store
│   │   ├── domain/
│   │   │   └── model/           # Domain entities
│   │   ├── application/
│   │   │   ├── dto/             # Response DTOs
│   │   │   ├── mapper/          # Mappers with MapStruct
│   │   │   ├── port/
│   │   │   │   ├── in/          # Ports input (use case)
│   │   │   │   └── out/         # Ports ouput (repositories)
│   │   │   └── service/         # Services (Use cases implementations)
│   │   └── infrastructure/
│   │       ├── persistence/
│   │       │   ├── adapter/     # Resistance adapters
│   │       │   └── repository/  # JPA Repositories
│   │       ├── security/        # Security settings
│   │       └── web/
│   │           ├── controller/   # REST controllers
│   │           ├── config/       # Web configuration
│   │           └── exception/    # Exception Handling
│   └── resources/
│       ├── application.yml       # Main settings
│       ├── application-test.yml  # Test Setup
│       └── data.sql              # Test data
└── test/
    └── java/com/spring/boot/project/ms/manga/store
        ├── application/service/  # Test services
        └── infrastructure/web/controller/  # Test controllers
```

## 📄 License

This project is licensed under the MIT License. See the `LICENSE` file for details.