package com.attendance.attendance.request.university.course.requirement;

import lombok.Data;

@Data
public class UpdateCourseRequirementsRequest {
    private Long courseId;
    private String requirementDescription;
    private Integer attendancePercentage;
}
