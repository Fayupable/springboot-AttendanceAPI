# Attendance Application API


## Overview
The Attendance Application is a Spring Boot-based project designed to manage attendance records for universities. It includes features for user authentication, role management, and CRUD operations for university entities.

## Table of Contents
- [Getting Started](#getting-started)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [Built With](#built-with)
- [Contributing](#contributing)
- [License](#license)

## Getting Started
These instructions will help you set up and run the project on your local machine for development and testing purposes.

### Prerequisites
- Java 17 or higher
- Maven 3.6.0 or higher
- MySQL 8.0 or higher


### Installation
1. Clone the repository:
    ```sh
    git clone https://github.com/Fayupable/attendance.git
    cd attendance
    ```


2. Update the database configuration in `src/main/resources/application.properties`:
    ```ini
    spring.datasource.url=YOUR_DATABASE_URL
    spring.datasource.username=YOUR_DATABASE_USERNAME
    spring.datasource.password=YOUR_DATABASE_PASSWORD
    ```

3. Create the database:
    ```sql
    CREATE DATABASE attendance;
    ```




### Configuration
Update the `application.properties` file with your specific settings:
```ini
spring.application.name=attendance
server.port=8080
spring.datasource.url=YOUR_DATABASE_URL
spring.datasource.username=YOUR_DATABASE_USERNAME
spring.datasource.password=YOUR_DATABASE_PASSWORD
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.show-sql=true
spring.main.banner-mode=off
logging.level.root=Info
logging.level.com.attendanceapp=DEBUG
spring.servlet.multipart.max-file-size=5MB
spring.servlet.multipart.max-request-size=5MB
api.prefix=/api/v1
auth.token.expirationInMils=3600000
auth.token.jwtSecret=36763979244226452948404D635166546A576D5A7134743777217A25432A4620
```


### Running the Application
To run the application, use the following command:
```sh
mvn spring-boot:run
```

## API Endpoints

### Authentication
- **POST** `/api/v1/auth/login` - Authenticate a user and return a JWT token.


### Universities
- **GET** `/api/v1/universities` - Retrieve a list of all universities.
- **POST** `/api/v1/universities` - Create a new university.
- **GET** `/api/v1/universities/{id}` - Retrieve a university by ID.
- **PUT** `/api/v1/universities/{id}` - Update a university by ID.
- **DELETE** `/api/v1/universities/{id}` - Delete a university by ID.

### Students
- **GET** `/api/v1/students` - Retrieve a list of all students.
- **POST** `/api/v1/students` - Create a new student.
- **GET** `/api/v1/students/{id}` - Retrieve a student by ID.
- **PUT** `/api/v1/students/{id}` - Update a student by ID.
- **DELETE** `/api/v1/students/{id}` - Delete a student by ID.

### Courses
- **GET** `/api/v1/courses` - Retrieve a list of all courses.
- **POST** `/api/v1/courses` - Create a new course.
- **GET** `/api/v1/courses/{id}` - Retrieve a course by ID.
- **PUT** `/api/v1/courses/{id}` - Update a course by ID.
- **DELETE** `/api/v1/courses/{id}` - Delete a course by ID.

## API Endpoints

### Course Attendance

#### Retrieve All Course Attendance
- **GET** `/api/v1/course-attendance/all`
    - **Description**: Retrieve a list of all course attendance records.
    - **Response**:
      ```json
      {
        "message": "Course Attendance retrieved successfully",
        "data": [ ... ] // List of CourseAttendanceDto
      }
      ```

#### Retrieve Course Attendance by ID
- **GET** `/api/v1/course-attendance/courseAttendanceId/{courseAttendanceId}`
    - **Description**: Retrieve a course attendance record by its ID.
    - **Path Variables**:
        - `courseAttendanceId` (Long): ID of the course attendance record.
    - **Response**:
      ```json
      {
        "message": "Course Attendance retrieved successfully",
        "data": { ... } // CourseAttendanceDto
      }
      ```

#### Retrieve Course Attendance by Student ID, Course ID, and Attendance Date
- **GET** `/api/v1/course-attendance/studentId/{studentId}/courseId/{courseId}/attendanceDate/{attendanceDate}`
    - **Description**: Retrieve course attendance records by student ID, course ID, and attendance date.
    - **Path Variables**:
        - `studentId` (Long): ID of the student.
        - `courseId` (Long): ID of the course.
        - `attendanceDate` (LocalDate): Date of attendance.
    - **Response**:
      ```json
      {
        "message": "Course Attendance retrieved successfully",
        "data": [ ... ] // List of CourseAttendanceDto
      }
      ```

#### Retrieve Course Attendance by Student ID and Course ID
- **GET** `/api/v1/course-attendance/studentId/{studentId}/courseId/{courseId}`
    - **Description**: Retrieve course attendance records by student ID and course ID.
    - **Path Variables**:
        - `studentId` (Long): ID of the student.
        - `courseId` (Long): ID of the course.
    - **Response**:
      ```json
      {
        "message": "Course Attendance retrieved successfully",
        "data": [ ... ] // List of CourseAttendanceDto
      }
      ```

#### Retrieve Course Attendance by Course Name
- **GET** `/api/v1/course-attendance/courseName/{courseName}`
    - **Description**: Retrieve course attendance records by course name.
    - **Path Variables**:
        - `courseName` (String): Name of the course.
    - **Response**:
      ```json
      {
        "message": "Course Attendance retrieved successfully",
        "data": [ ... ] // List of CourseAttendanceDto
      }
      ```

#### Retrieve Course Attendance by Course ID
- **GET** `/api/v1/course-attendance/courseId/{courseId}`
    - **Description**: Retrieve course attendance records by course ID.
    - **Path Variables**:
        - `courseId` (Long): ID of the course.
    - **Response**:
      ```json
      {
        "message": "Course Attendance retrieved successfully",
        "data": [ ... ] // List of CourseAttendanceDto
      }
      ```

#### Retrieve Course Attendance by Student ID
- **GET** `/api/v1/course-attendance/studentId/{studentId}`
    - **Description**: Retrieve course attendance records by student ID.
    - **Path Variables**:
        - `studentId` (Long): ID of the student.
    - **Response**:
      ```json
      {
        "message": "Course Attendance retrieved successfully",
        "data": [ ... ] // List of CourseAttendanceDto
      }
      ```

#### Retrieve Course Attendance by Student ID and Course Name
- **GET** `/api/v1/course-attendance/studentId/{studentId}/courseName/{courseName}`
    - **Description**: Retrieve course attendance records by student ID and course name.
    - **Path Variables**:
        - `studentId` (Long): ID of the student.
        - `courseName` (String): Name of the course.
    - **Response**:
      ```json
      {
        "message": "Course Attendance retrieved successfully",
        "data": [ ... ] // List of CourseAttendanceDto
      }
      ```

#### Add Course Attendance
- **POST** `/api/v1/course-attendance/add`
    - **Description**: Add a new course attendance record.
    - **Request Body**:
      ```json
      {
        "studentId": Long,
        "courseId": Long,
        "attendanceDate": LocalDate,
        "status": String
      }
      ```
    - **Response**:
      ```json
      {
        "message": "Course Attendance added successfully",
        "data": { ... } // CourseAttendanceDto
      }
      ```

#### Update Course Attendance
- **PUT** `/api/v1/course-attendance/update-course-attendance/{courseAttendanceId}`
    - **Description**: Update an existing course attendance record.
    - **Path Variables**:
        - `courseAttendanceId` (Long): ID of the course attendance record.
    - **Request Body**:
      ```json
      {
        "studentId": Long,
        "courseId": Long,
        "attendanceDate": LocalDate,
        "status": String
      }
      ```
    - **Response**:
      ```json
      {
        "message": "Course Attendance updated successfully",
        "data": { ... } // CourseAttendanceDto
      }
      ```

#### Delete Course Attendance
- **DELETE** `/api/v1/course-attendance/delete-course-attendance/{courseAttendanceId}`
    - **Description**: Delete a course attendance record.
    - **Path Variables**:
        - `courseAttendanceId` (Long): ID of the course attendance record.
    - **Response**:
      ```json
      {
        "message": "Course Attendance deleted successfully",
        "data": null
      }
      ```
      

## Built With
- [Spring Boot](https://spring.io/projects/spring-boot) - The framework used
- [Maven](https://maven.apache.org/) - Dependency Management
- [MySQL](https://www.mysql.com/) - Database

## Contributing
Please read `CONTRIBUTING.md` for details on our code of conduct and the process for submitting pull requests.
