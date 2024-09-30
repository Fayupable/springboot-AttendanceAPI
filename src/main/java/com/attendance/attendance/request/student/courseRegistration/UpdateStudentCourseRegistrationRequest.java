package com.attendance.attendance.request.student.courseRegistration;

import lombok.Data;

@Data
public class UpdateStudentCourseRegistrationRequest {
    private Long id;
    private Long studentId;
    private Long courseId;
    private String registrationDate;
    private String status;
}
