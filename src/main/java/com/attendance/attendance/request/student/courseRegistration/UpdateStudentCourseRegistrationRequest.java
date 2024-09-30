package com.attendance.attendance.request.student.courseRegistration;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateStudentCourseRegistrationRequest {
    private Long id;
    private Long studentId;
    private Long courseId;
    private LocalDate registrationDate;
    private String status;
}
