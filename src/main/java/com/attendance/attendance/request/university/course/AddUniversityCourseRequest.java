package com.attendance.attendance.request.university.course;

import lombok.Data;

@Data
public class AddUniversityCourseRequest {
    private Long departmentId;
    private String courseCode;
    private String courseName;
    private String description;

    public Long getUniversityId() {
        return departmentId;
    }
}
