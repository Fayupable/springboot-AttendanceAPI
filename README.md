# Attendance Application API


## Overview
The Attendance Application is a Spring Boot-based project designed to manage attendance records for students and teachers. The application allows users to create, update, and delete attendance records, as well as generate reports based on the data. The application also provides endpoints for managing students, teachers, courses, and universities.

## Table of Contents
- [Getting Started](#getting-started)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [Running the Application](#running-the-application)
- [Table](#table)
- [Dependencies](#dependencies)
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

## Table
![image alt](https://github.com/Fayupable/springboot-AttendanceAPI/blob/main/doc/AttendanceNew.png?raw=true)

## Dependencies
![image alt](https://github.com/Fayupable/springboot-AttendanceAPI/blob/main/doc/dependcy.png?raw=true)

## API Endpoints

### Authentication
- **POST** `/api/v1/auth/login` - Authenticate a user and return a JWT token.

### CourseAttendances
- **GET** `/api/v1/course-attendances` - Retrieve a list of all course attendances.
- **GET** `/api/v1/course-attendances/courseAttendanceId/{courseAttendanceId}` - Retrieve a course attendance by ID.
- **GET** `/api/v1/course-attendances/studentId/{studentId}/courseId/{courseId}/attendanceDate/{attendanceDate}` - Retrieve a course attendance by student ID, course ID, and attendance date.
- **GET** `/api/v1/course-attendances/studentId/{studentId}/courseId/{courseId}` - Retrieve a course attendance by student ID and course ID.
- **GET** `/api/v1/course-attendances/courseName/{courseName}` - Retrieve a course attendance by course name.
- **GET** `/api/v1/course-attendances/studentId/{studentId}` - Retrieve a course attendance by student ID.
- **GET** `/api/v1/course-attendances/studentId/{studentId}/courseName/{courseName}` - Retrieve a course attendance by student ID and course name.
- **POST** `/api/v1/course-attendances/add` - Create a new course attendance.
- **PUT** `/api/v1/course-attendances/update-course-attendance/{courseAttendanceId}` - Update a course attendance by ID.
- **DELETE** `/api/v1/course-attendances/delete-course-attendance/{courseAttendanceId}` - Delete a course attendance by ID.

### GeneralReports
- **GET** `/api/v1/general-reports/all` - Retrieve a list of all general reports.
- **GET** `/api/v1/general-reports/generalReportId/{generalReportId}` - Retrieve a general report by ID.
- **GET** `/api/v1/general-reports/generalReportDate/{generalReportDate}` - Retrieve a general report by date.
- **GET** `/api/v1/general-reports/generalReportStartDate/{generalReportStartDate}/generalReportEndDate/{generalReportEndDate}` - Retrieve general reports between two dates.
- **POST** `/api/v1/general-reports/add` - Create a new general report.
- **PUT** `/api/v1/general-reports/update/{generalReportId}` - Update a general report by ID.
- **DELETE** `/api/v1/general-reports/delete/{generalReportId}` - Delete a general report by ID.

### Images
- **GET** `/api/v1/images/image/download/{imageId}` - Download an image by ID.
- **POST** `/api/v1/images/image/upload` - Upload an image.
- **PUT** `/api/v1/images/image/{imageId}/update` - Update an image by ID.
- **PUT** `/api/v1/images/image/{imageId}/delete` - Delete an image by ID.

### Person
- **GET** `/api/v1/person/{personId}` - Retrieve a person by ID.
- **GET** `/api/v1/person/all` - Retrieve a list of all persons.
- **GET** `/api/v1/person/personName/{personName}` - Retrieve persons by name.
- **GET** `/api/v1/person/personEmail/{personEmail}` - Retrieve persons by email.
- **POST** `/api/v1/person/add` - Add a new person.
- **PUT** `/api/v1/person/{personId}/update` - Update a person by ID.
- **DELETE** `/api/v1/person/{personId}/delete` - Delete a person by ID.

### Student
- **GET** `/api/v1/student/{studentId}/student` - Retrieve a student by ID.
- **GET** `/api/v1/student/all` - Retrieve a list of all students.
- **GET** `/api/v1/student/studentName/{studentName}` - Retrieve students by name.
- **GET** `/api/v1/student/studentEmail/{studentEmail}` - Retrieve students by email.
- **POST** `/api/v1/student/add` - Add a new student.
- **PUT** `/api/v1/student/{studentId}/update` - Update a student by ID.
- **DELETE** `/api/v1/student/{studentId}/delete` - Delete a student by ID.

### Student Course Registration
- **GET** `/api/v1/student/courseRegistration/all` - Retrieve a list of all student course registrations.
- **GET** `/api/v1/student/courseRegistration/studentId/{studentId}` - Retrieve student course registrations by student ID.
- **GET** `/api/v1/student/courseRegistration/courseId/{courseId}` - Retrieve student course registrations by course ID.
- **GET** `/api/v1/student/courseRegistration/studentId/{studentId}/courseId/{courseId}` - Retrieve student course registrations by student ID and course ID.
- **GET** `/api/v1/student/courseRegistration/studentId/{studentId}/courseId/{courseId}/status/{status}` - Retrieve student course registrations by student ID, course ID, and status.
- **GET** `/api/v1/student/courseRegistration/studentId/{studentId}/status/{status}` - Retrieve student course registrations by student ID and status.
- **POST** `/api/v1/student/courseRegistration/add-student-course-registration` - Add a new student course registration.
- **PUT** `/api/v1/student/courseRegistration/update-student-course-registration/{id}` - Update a student course registration by ID.
- **DELETE** `/api/v1/student/courseRegistration/delete-student-course-registration/{id}` - Delete a student course registration by ID.

### Teacher
- **GET** `/api/v1/teacher/all` - Retrieve a list of all teachers.
- **GET** `/api/v1/teacher/email/{email}` - Retrieve teachers by email.
- **GET** `/api/v1/teacher/name/{name}` - Retrieve teachers by name.
- **GET** `/api/v1/teacher/university/{universityId}` - Retrieve teachers by university ID.
- **GET** `/api/v1/teacher/department/{departmentId}` - Retrieve teachers by department ID.
- **POST** `/api/v1/teacher/add` - Add a new teacher.
- **PUT** `/api/v1/teacher/update/{teacherId}` - Update a teacher by ID.
- **DELETE** `/api/v1/teacher/delete/{teacherId}` - Delete a teacher by ID.



### Universities
- **GET** `/api/v1/universities` - Retrieve a list of all universities.
- **POST** `/api/v1/universities` - Create a new university.
- **GET** `/api/v1/universities/{id}` - Retrieve a university by ID.
- **PUT** `/api/v1/universities/{id}` - Update a university by ID.
- **DELETE** `/api/v1/universities/{id}` - Delete a university by ID.


### University Course
- **GET** `/api/v1/university-course/all` - Retrieve a list of all university courses.
- **GET** `/api/v1/university-course/course/{courseId}` - Retrieve a university course by ID.
- **GET** `/api/v1/university-course/name/{courseName}` - Retrieve university courses by name.
- **GET** `/api/v1/university-course/code/{courseCode}` - Retrieve university courses by code.
- **POST** `/api/v1/university-course/add` - Add a new university course.
- **PUT** `/api/v1/university-course/update/{courseId}/department/{departmentId}` - Update a university course by ID and department ID.
- **DELETE** `/api/v1/university-course/delete/{courseId}` - Delete a university course by ID.


### University Course Details
- **GET** `/api/v1/university-course-details/all` - Retrieve a list of all university course details.
- **GET** `/api/v1/university-course-details/course/{courseId}` - Retrieve university course details by course ID.
- **GET** `/api/v1/university-course-details/details/{id}` - Retrieve university course details by ID.
- **POST** `/api/v1/university-course-details/courses/details/add` - Add new university course details.
- **PUT** `/api/v1/university-course-details/courses/{courseId}/details/update/{id}` - Update university course details by course ID and details ID.
- **DELETE** `/api/v1/university-course-details/delete/{id}` - Delete university course details by ID.


### University Course Requirements
- **GET** `/api/v1/university-course-requirements/all` - Retrieve a list of all university course requirements.
- **GET** `/api/v1/university-course-requirements/courseId/{courseId}` - Retrieve university course requirements by course ID.
- **GET** `/api/v1/university-course-requirements/courseRequirementsId/{courseRequirementsId}` - Retrieve university course requirements by ID.
- **POST** `/api/v1/university-course-requirements/add-course-requirements` - Add new university course requirements.
- **PUT** `/api/v1/university-course-requirements/update-course-requirements/{id}/course/{courseId}` - Update university course requirements by ID and course ID.
- **DELETE** `/api/v1/university-course-requirements/delete-course-requirements/{id}` - Delete university course requirements by ID.


### University Department
- **GET** `/api/v1/university-department/all` - Retrieve a list of all university departments.
- **GET** `/api/v1/university-department/{departmentId}` - Retrieve a university department by ID.
- **GET** `/api/v1/university-department/department/{departmentName}` - Retrieve university departments by name.
- **POST** `/api/v1/university-department/add` - Add a new university department.
- **PUT** `/api/v1/university-department/department/{departmentId}/update` - Update a university department by ID.
- **DELETE** `/api/v1/university-department/department/{departmentId}/delete` - Delete a university department by ID.


### User Reports
- **GET** `/api/v1/user-reports/all` - Retrieve a list of all user reports.
- **GET** `/api/v1/user-reports/userReportId/{userReportId}` - Retrieve a user report by ID.
- **GET** `/api/v1/user-reports/userReportPersonId/{userReportPersonId}` - Retrieve user reports by person ID.
- **GET** `/api/v1/user-reports/userReportDate/{userReportDate}` - Retrieve user reports by date.
- **GET** `/api/v1/user-reports/userReportStartDate/{userReportStartDate}/userReportEndDate/{userReportEndDate}` - Retrieve user reports between dates.
- **POST** `/api/v1/user-reports/add` - Add a new user report.
- **PUT** `/api/v1/user-reports/update/{reportId}` - Update a user report by ID.
- **DELETE** `/api/v1/user-reports/delete/{userReportId}` - Delete a user report by ID.

## Built With
- [Spring Boot](https://spring.io/projects/spring-boot) - The framework used
- [Maven](https://maven.apache.org/) - Dependency Management
- [MySQL](https://www.mysql.com/) - Database

## Contributing
Please read `CONTRIBUTI
