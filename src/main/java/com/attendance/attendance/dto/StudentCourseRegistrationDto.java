package com.attendance.attendance.dto;

import lombok.Data;

@Data
public class StudentCourseRegistrationDto {
    private Long id;
    private Long studentId;
    private Long courseId;
    private String registrationDate;
    private String status;
}
