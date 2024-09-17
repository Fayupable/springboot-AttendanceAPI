package com.attendance.attendance.request.university.course;

import lombok.Data;

@Data
public class UpdateUniversityCourseRequest {
    private Long courseId;
    private Long departmentId;
    private String courseCode;
    private String courseName;
    private String description;
}
