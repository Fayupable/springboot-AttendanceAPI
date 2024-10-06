package com.attendance.attendance.request.university.course.requirement;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Add Course Requirements Request", description = "Request to add course requirements")
public class AddCourseRequirementsRequest {

    @Schema(description = "ID of the course", example = "12345")
    private Long courseId;

    @Schema(description = "Description of the requirement", example = "Minimum attendance required")
    private String requirementDescription;

    @Schema(description = "Required attendance percentage", example = "75")
    private Integer attendancePercentage;
}