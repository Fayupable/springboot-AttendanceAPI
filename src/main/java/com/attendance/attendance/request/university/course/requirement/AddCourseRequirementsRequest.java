package com.attendance.attendance.request.university.course.requirement;

import lombok.Data;

@Data
public class AddCourseRequirementsRequest {

        private Long courseId;

        private String courseRequirements;
}
