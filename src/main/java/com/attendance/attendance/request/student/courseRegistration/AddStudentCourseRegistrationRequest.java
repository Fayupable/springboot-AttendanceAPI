package com.attendance.attendance.request.student.courseRegistration;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class AddStudentCourseRegistrationRequest {
    private Long studentId;
    private Long courseId;
    private LocalDateTime registrationDate;
    private String status;
}
