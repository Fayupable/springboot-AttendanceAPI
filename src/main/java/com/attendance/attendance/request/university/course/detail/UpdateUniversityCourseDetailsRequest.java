package com.attendance.attendance.request.university.course.detail;

import lombok.Data;

@Data
public class UpdateUniversityCourseDetailsRequest {
    private Long courseId;
    private String detailedDescription;
}
