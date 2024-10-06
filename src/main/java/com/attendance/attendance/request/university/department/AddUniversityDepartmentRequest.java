package com.attendance.attendance.request.university.department;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "Add University Department Request", description = "Request to add a new department to a university, including department name and associated university ID")
public class AddUniversityDepartmentRequest {

    @NotBlank(message = "Department name is required")
    @Size(max = 100, message = "Department name cannot exceed 100 characters")
    @Schema(description = "Name of the department", example = "Computer Science")
    private String departmentName;

    @NotNull(message = "University id is required")
    @Schema(description = "ID of the university to which the department belongs", example = "1")
    private Long universityId;
}