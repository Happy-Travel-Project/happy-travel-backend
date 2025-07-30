# ✈️ Nomad Pulse - Travel Management API

A robust REST API for managing users and travel destinations, built with **Spring Boot** and modern Java technologies. Ideal for platforms that allow users to explore, register, and manage travel experiences.

![Java](https://img.shields.io/badge/Java-17-blue?style=flat-square)  
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.4-brightgreen?style=flat-square)  
![Maven](https://img.shields.io/badge/Maven-3.8.1-blue?style=flat-square)  
![REST API](https://img.shields.io/badge/API-RESTful-orange?style=flat-square)

---

## 📋 Table of Contents

- [✨ Features](#-features)
- [🚀 Getting Started](#-getting-started)
- [⚙️ Installation](#-installation)
- [🏃‍♂️ Running the App](#-running-the-app)
- [📚 API Documentation](#-api-documentation)
- [🌐 Main Endpoints](#-main-endpoints)
- [🔧 Technologies](#-technologies)
- [📊 Architecture](#-architecture)
- [🧪 Tests](#-tests)
- [🤝 Contributing](#-contributing)
- [👥 Team Members](#-team-members)


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

### 1. Clone the Repository

```
git clone https://github.com/Happy-Travel-Project/happy-travel-backend.git
cd happy-travel-backend 
```

### 2. Configure Database

Edit `src/main/resources/application.yaml` with your database credentials:
```
spring:
  output:
    ansi:
      enabled: ALWAYS
  application:
    name: happy-travel
  config:
    import: optional:file:.env[.properties]

  datasource:
    url: jdbc:mysql://localhost:3306/happy-travel?createDatabaseIfNotExist=true
    username: your_username
    password: your_password

  sql:
    init:
      mode: always

  jpa:
    hibernate:
      ddl-auto: create-drop  # update
      defer-datasource-initialization: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQLDialect
        format_sql: true
        show-sql: true

server:
  port: 8080

```

### 3. Build the Project
```
./mvnw clean install
```

## 📚 API Documentation

### Flowchart
[Open Flowchart](https://drive.google.com/file/d/18U-z4jwBex4TKJuk2Fu8NRszMeL1wERu/view)

## 🌐 API Endpoints

### **ENDPOINTS**

#### 🔓 Publics (Auth: All)
| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/register` | Register new users |
| `POST` | `/login` | User login and JWT generation |
| `GET`  | `/destinations` | List all available destinations |
| `GET`  | `/destinations/title/{title}` | Filter by title |
| `GET`  | `/destinations/country/{country}` | Filter by country |
| `GET`  | `/destinations/city/{city}` | Filter by city |
| `GET`  | `/destinations/{id}` | View destination details |
| `GET`  | `/destinations/{id}` | View destination details |

---

#### 🔐 Authenticated Users (Auth: User)
| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET`    | `/destinations/user` | Get information about the authenticated user |
| `POST`   | `/destinations` | Create a destination associated with the authenticated user |
| `PUT`    | `/destinations/{id}` | Edit your own destination |
| `DELETE` | `/destinations/{id}` | Delete your own destination |

---

#### 🛡️ Administrators (Auth: Admin)
| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET`  | `/users` | List all users |
| `PUT`  | `/admin/users/{id}` | Modify user data or role |
| `GET`  | `/users/{id}` | Get a user's destinations |



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
├── src/
│ ├── main/
│ │ ├── java/com/example/happy_travel/
│ │ │ ├── config/ # CORS and security configurations
│ │ │ ├── controllers/ # REST controllers (Auth, Destination, User)
│ │ │ ├── dtos/ # Request and response DTOs
│ │ │ ├── exceptions/ # Custom exceptions and handler
│ │ │ ├── models/ # Entity classes (User, Destination, Role)
│ │ │ ├── repositories/ # Spring Data JPA repositories
│ │ │ ├── security/ # JWT and authentication config
│ │ │ ├── services/ # Business logic services
│ │ │ └── HappyTravelApplication.java # Main Spring Boot application
│ │ └── resources/
│ │ ├── application.properties
│ │ └── data.sql
│ └── test/
│ └── java/com/example/happy_travel/
│ ├── controllers/ # Controller unit tests
│ ├── services/ # Service unit tests
│ └── HappyTravelApplicationTests.java
├── pom.xml # Maven project descriptor
├── mvnw / mvnw.cmd # Maven wrapper scripts
├── .gitignore / .gitattributes
├── README.md
```

### 🧪 Tests
Unit tests for services 

Light integration tests for controllers

Run tests with:
```
./mvnw test
```
## 🤝 Contributing
We welcome contributions! Follow these steps:

1. Fork the repo

2. Create a new branch:
   git checkout -b feature/your-feature-name

3. Commit your changes:
   git commit -m "Add your feature name"

4. Push and open a Pull Request


## 👥 Team Members
<table> <tr> <td align="center"> <a href="https://github.com/acpp2510"> <img src="https://github.com/acpp2510.png" width="100px;" alt="acpp2510"/> <br /> <sub><b>acpp2510</b></sub> </a> <br /> <sub>Product Owner</sub> </td> <td align="center"> <a href="https://github.com/morenaperalta"> <img src="https://github.com/morenaperalta.png" width="100px;" alt="morenaperalta"/> <br /> <sub><b>morenaperalta</b></sub> </a> <br /> <sub>Scrum Master</sub> </td> <td align="center"> <a href="https://github.com/thaisrqueiroz"> <img src="https://github.com/thaisrqueiroz.png" width="100px;" alt="thaisrqueiroz"/> <br /> <sub><b>thaisrqueiroz</b></sub> </a> <br /> <sub>Developer</sub> </td> <td align="center"> <a href="https://github.com/PaolaAPL17"> <img src="https://github.com/PaolaAPL17.png" width="100px;" alt="PaolaAPL17"/> <br /> <sub><b>PaolaAPL17</b></sub> </a> <br /> <sub>Developer</sub> </td> </tr> </table>