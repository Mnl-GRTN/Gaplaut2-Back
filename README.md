# Gaplaut2 

Gaplaut2 is a web application for managing a fleet of medical centers. 
Here you can find its backend developed with Java Spring Boot and a PostgreSQL database.

## Installation

### Requirements

- Git
- Java 
- Gradle
- PostgreSQL (make sure the server is running)

### Run the application

1. Clone the repository
```bash
git clone https://github.com/Mnl-GRTN/Gaplaut2-Back.git
cd Gaplaut2-Back
```
2. Change the properties in the `application.yaml` file to match your database configuration

3. Change the properties in the `build.gradle` file to match your Gradle/Java configuration

4. Run the application
```bash
gradle run
```

5. The application should be running on `http://localhost:8080`


## Database schema

Here is the database schema of the application based on the instructions given in the subject. (see images below)
    

### Center
    - id
    - name
    - address
    - zipCode
    - city

### Doctor
    - id
    - lastName
    - firstName
    - email
    - password
    - centerId

### Booking
    - id
    - lastName
    - firstName
    - email
    - phoneNumber
    - date
    - centerId
    - isVaccinated

### Role
    - id
    - name

### Role_Doctor
    - roleId
    - doctorId


## Instructions of the assignment

### Public application for booking appointments
<img src="README_img/publicApp.jpg"></img>

### Back office application for managing the fleet of medical centers
<img src="README_img/backOfficeApp.jpg"></img>
