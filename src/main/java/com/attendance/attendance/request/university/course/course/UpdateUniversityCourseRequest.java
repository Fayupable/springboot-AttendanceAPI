package com.attendance.attendance.request.university.course.course;

import lombok.Data;

@Data
public class UpdateUniversityCourseRequest {
    private String courseCode;
    private String courseName;
    private String description;
}