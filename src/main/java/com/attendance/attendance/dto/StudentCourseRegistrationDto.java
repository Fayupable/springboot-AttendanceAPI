package com.attendance.attendance.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StudentCourseRegistrationDto {
    private Long studentId;
    private Long courseId;
    private LocalDateTime registrationDate;
    private String status;
}
