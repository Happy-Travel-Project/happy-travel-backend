# ✈️ Happy Travel - Travel Management API

A robust REST API for managing users and travel destinations, built with **Spring Boot** and modern Java technologies. Ideal for platforms that allow users to explore, register, and manage travel experiences.

![Java](https://img.shields.io/badge/Java-17-blue?style=flat-square)  
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.4-brightgreen?style=flat-square)  
![Maven](https://img.shields.io/badge/Maven-3.8.1-blue?style=flat-square)  
![REST API](https://img.shields.io/badge/API-RESTful-orange?style=flat-square)

---

## 📋 Table of Contents

- ✨ Features
- 🚀 Getting Started
- ⚙️ Installation
- 🏃‍♂️ Running the App
- 📚 API Documentation
- 🌐 Main Endpoints
- 🔧 Technologies
- 📊 Architecture
- 🧪 Tests
- 🤝 Contributing
- 👥 Team Members

---

## ✨ Features

- 👤 User management (registration, login, roles)
- 🌍 Destination management
- 🔐 Security with Spring Security and JWT
- ✅ Custom validations with Bean Validation
- 🚨 Global exception handling
- 📖 API documentation with Postman

---

## 🚀 Getting Started

### Prerequisites

Make sure you have the following installed:

- Java 17+
- Maven 3.8+
- MySQL 8+
- Git

Quick Start
```
git clone https://github.com/Happy-Travel-Project/happy-travel-backend.git
cd happy-travel-backend
```

Build the project
```
./mvnw clean install
```

Run the application
```
./mvnw spring-boot:run
```

The API will be available at:
http://localhost:8080/api

## ⚙️ Installation

1. Clone the Repository

```
git clone https://github.com/Happy-Travel-Project/happy-travel-backend.git
cd happy-travel-backend 
```

### 2. Configure Database
Edit `src/main/resources/application.properties:`

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/happy_travel
    username: your_username
    password: your_password

jpa:
  hibernate:
    ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQL8Dialect

  server:
  port: 8080
```
### 3. Build the Project
```bash
./mvnw clean install
```

## 📚 API Documentation

### Flowchart
![Flowchart]()


### Postman Collection: 
Access our comprehensive API collection:
[🔗 **Join Postman Team**](https://bold-crater-329571.postman.co/workspace/Team-Workspace~7575921-ee7e-4eeb-8379-ecc11e32e832/overview)

### Base URL
```
http://localhost:8080/api
```
## 🌐 API Endpoints

| Método | Endpoint | Descripción | Auth | Hecho? |
| --- | --- | --- | --- | --- |
| `POST` | `/register` | Registro de nuevos usuarios | All | ✅  |
| `POST` | `/login` | Login de usuarios y generación de JWT | All | ✅  |
| `GET` | `/destinations` | Listar todos los destinos disponibles | All | ✅  |
| `GET` | `/destinations/title/{title}` | Filtros por título | All | ✅  |
| `GET` | `/destinations/country/{country}` | Filtros por país | All | ✅  |
| `GET` | `/destinations/city/{city}` | Filtros por ciudad | All | ✅  |
| `GET` | `/destinations/{id}` | Ver detalles de un destino | All | ✅  |
| `GET` | `/destinations/{id}` | Ver detalles de un destino (revisar, porque puse permitAll) | All | ✅  |
| `GET` | `/destinations/user` | Obtener información (destinos? profile? todo?) del usuario autenticado | User | ✅  |
| `POST` | `/destinations` | Crear un destino asociado al usuario autenticado | User | ✅  |
| `PUT` | `/destinations/{id}` | Editar un destino propio | User, Admin | ✅  |
| `DELETE` | `/destinations/{id}` | Eliminar un destino propio | User, Admin | ✅  |
| `GET` | `/users` | Listar todos los usuarios | Admin | ✅  |
| `PUT` | `/admin/users/{id}` | Modificar datos o rol de un usuario | Admin | ✅  |
| `GET` | `/users/{id}` | Obtener los destinos de un usuario | Admin | In progress |

## 🔧 Technologies

### Backend Stack
- **Java 17+** - Core programming language

- **Spring Boot 3.2.4+** - Application framework

- **Spring Data JPA** - Data persistence layer

- **Spring Security + JWT** - Provides authentication and authorization 

- **Hibernate** - ORM framework

- **MySQL** - Database systems

### Development Tools
- **JUnit 5** - Testing framework

- **Mockito** - Mocking framework

- **Postman** - API testing and documentation

- **Maven** - Build automation and dependency management tool

## 📊 Architecture

The project follows a clean 3-layer MVC architecture:

Controller → Service → Repository

### 📁 Project Structure


```
happy-travel-backend/
├── .mvn/
│   └── wrapper/
├── htmlReport/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── happy_travel/
│   │   │               ├── config/
│   │   │               │   └── CorsConfig.java
│   │   │               ├── controllers/
│   │   │               │   ├── AuthController.java
│   │   │               │   ├── DestinationController.java
│   │   │               │   └── UserController.java
│   │   │               ├── dtos/
│   │   │               │   ├── destination/
│   │   │               │   │   ├── DestinationMapper.java
│   │   │               │   │   ├── DestinationRequest.java
│   │   │               │   │   └── DestinationResponse.java
│   │   │               │   └── user/
│   │   │               │       ├── JwtResponse.java
│   │   │               │       ├── UserMapper.java
│   │   │               │       ├── UserRequest.java
│   │   │               │       ├── UserRequestByAdmin.java
│   │   │               │       └── UserResponse.java
│   │   │               ├── exceptions/
│   │   │               │   ├── EntityAlreadyExistsException.java
│   │   │               │   ├── EntityNotFoundException.java
│   │   │               │   ├── ErrorResponse.java
│   │   │               │   └── GlobalExceptionHandler.java
│   │   │               ├── models/
│   │   │               │   ├── Destination.java
│   │   │               │   ├── Role.java
│   │   │               │   └── User.java
│   │   │               ├── repositories/
│   │   │               │   ├── DestinationRepository.java
│   │   │               │   └── UserRepository.java
│   │   │               ├── security/
│   │   │               │   └── jwt/
│   │   │               │       └── CustomUserDetail.java
│   │   │               │   └── SecurityConfig.java
│   │   │               ├── services/
│   │   │               └── HappyTravelApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── data.sql
│   └── test/
│       └── java/
│           └── com/
│               └── example/
│                   └── happy_travel/
│                       ├── controllers/
│                       │   ├── DestinationControllersTest.java
│                       │   └── UserControllersTest.java
│                       ├── services/
│                       │   ├── DestinationServicesTest.java
│                       │   └── UserServicesTest.java
│                       └── HappyTravelApplicationTests.java
├── .gitattributes
├── .gitignore
├── mvnw
├── mvnw.cmd
├── pom.xml
├── README.md
```

### 🧪 Tests
Unit tests for services and controllers

Integration tests with H2 (planned)

Run tests with:
```
./mvnw test
```
## 🤝 Contributing
We welcome contributions! Follow these steps:

1. Fork the repo

2. Create a new branch:
git checkout -b feature/amazing-feature

3. Commit your changes:
git commit -m "Add amazing feature"

4.Push and open a Pull Request


## 👥 Team Members
<table> <tr> <td align="center"> <a href="https://github.com/acpp2510"> <img src="https://github.com/acpp2510.png" width="100px;" alt="acpp2510"/> <br /> <sub><b>acpp2510</b></sub> </a> <br /> <sub>Product Owner</sub> </td> <td align="center"> <a href="https://github.com/morenaperalta"> <img src="https://github.com/morenaperalta.png" width="100px;" alt="morenaperalta"/> <br /> <sub><b>morenaperalta</b></sub> </a> <br /> <sub>Scrum Master</sub> </td> <td align="center"> <a href="https://github.com/thaisrqueiroz"> <img src="https://github.com/thaisrqueiroz.png" width="100px;" alt="thaisrqueiroz"/> <br /> <sub><b>thaisrqueiroz</b></sub> </a> <br /> <sub>Developer</sub> </td> <td align="center"> <a href="https://github.com/PaolaAPL17"> <img src="https://github.com/PaolaAPL17.png" width="100px;" alt="PaolaAPL17"/> <br /> <sub><b>PaolaAPL17</b></sub> </a> <br /> <sub>Developer</sub> </td> </tr> </table>