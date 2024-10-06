package com.attendance.attendance.request.university.course.requirement;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Update Course Requirements Request", description = "Request to update course requirements")
public class UpdateCourseRequirementsRequest {

    @Schema(description = "Description of the requirement", example = "Minimum attendance required")
    private String requirementDescription;

    @Schema(description = "Required attendance percentage", example = "75")
    private Integer attendancePercentage;
}