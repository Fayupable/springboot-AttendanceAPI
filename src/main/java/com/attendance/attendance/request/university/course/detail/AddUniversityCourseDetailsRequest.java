package com.attendance.attendance.request.university.course.detail;

import lombok.Data;

@Data
public class AddUniversityCourseDetailsRequest {
    private Long courseId;
    private String detailedDescription;
}
