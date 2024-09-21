package com.attendance.attendance.request.university.course.requirement;

import lombok.Data;

@Data
public class UpdateCourseRequirementsRequest {

    private Long id;

    private Long courseId;

    private String courseRequirements;
}
