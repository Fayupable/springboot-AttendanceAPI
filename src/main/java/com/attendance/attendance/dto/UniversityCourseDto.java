package com.attendance.attendance.dto;

import lombok.Data;

@Data
public class UniversityCourseDto {
    private Long courseId;
    private String courseName;
    private String courseCode;
    private String description;
    private Long departmentId;
    private String departmentName;
}
