package com.attendance.attendance.dto;

import lombok.Data;

@Data
public class CoursesRequirementsDto {
    private Long id;
    private Long courseId;
    private String requirementDescription;
    private Integer attendancePercentage;

}
