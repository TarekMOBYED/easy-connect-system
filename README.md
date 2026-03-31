# Easy Connect System

Easy Connect is a simple enterprise-style Java application for managing employees and internal communication within a company.  
The system demonstrates the use of **Java, JDBC, MySQL, and the DAO design pattern** in a layered architecture.

This project was developed as a learning project to practice backend development and database integration.

---

# Features

- User authentication (Admin / Mitarbeiter)
- Employee management
- Send internal messages
- Vacation registration
- Soft delete for employees
- Database persistence using MySQL
- Layered architecture using DAO pattern

---

## Console Example

![Console Example](images/console.png)

---

# Technologies Used

- Java
- JDBC
- MySQL
- Eclipse IDE
- DAO Design Pattern

---
The project follows a simple layered architecture using the DAO pattern.
Project Structure

src
├── app
│   └── TestApp.java          # Application entry point
│
├── dao                       # Data Access Layer
│   ├── DAO.java
│   ├── MitarbeiterDAO.java
│   └── MitarbeiterDAOImpl.java
│
├── model                     # Domain Models
│   ├── Mitarbeiter.java
│   ├── Schichtleiter.java
│   └── Schichtplan.java
│
└── util                      # Utility classes
    └── Database.java         # JDBC connection handling

---

##How to Run

1. Create MySQL database easy_connect
2. Run TestApp.java
3. Login with demo user
   
---

# Database Setup

Run the SQL script located in:
database/schema.sql

This will create the database:

easy_connect

and the following tables:

- mitarbeiter
- users

---

# Example Login

Admin account:
username: tarek
password: 1234

Regular user:
username: user1
password: pass

---

# Example Console Interface
=== Willkommen zum Easy Connect System ===

1- Neuen Mitarbeiter anlegen
2- Urlaub anmelden
3- Nachricht senden
4- Mitarbeiter anzeigen
5- Mitarbeiter entfernen
6- Beenden

---

# Learning Goals

This project demonstrates:

- Java database connectivity (JDBC)
- DAO pattern implementation
- Console-based enterprise system
- MySQL database design
- Basic role-based access control

---

# Author

Tarek Mobayed  
Medical Informatics Student  
FH Dortmund

---

# Future Improvements

Possible improvements for this project:

- GUI using JavaFX
- REST API with Spring Boot
- Password encryption
- Logging system
- Web interface
