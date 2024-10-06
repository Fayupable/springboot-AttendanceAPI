package com.attendance.attendance.request.university.course.course;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Add University Course Request", description = "The request to add a course to a university")
public class AddUniversityCourseRequest {

    @Schema(description = "ID of the department", example = "10")
    private Long departmentId;

    @Schema(description = "Code of the course", example = "CS101")
    private String courseCode;

    @Schema(description = "Name of the course", example = "Introduction to Computer Science")
    private String courseName;

    @Schema(description = "Description of the course", example = "This course provides an introduction to computer science.")
    private String description;
}