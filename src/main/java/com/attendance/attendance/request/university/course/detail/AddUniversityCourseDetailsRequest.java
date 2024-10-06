package com.attendance.attendance.request.university.course.detail;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Add University Course Details Request", description = "Request to add university course details")
public class AddUniversityCourseDetailsRequest {

    @Schema(description = "ID of the course", example = "12345")
    private Long courseId;

    @Schema(description = "Detailed description of the course", example = "This course covers advanced topics in computer science.")
    private String detailedDescription;
}