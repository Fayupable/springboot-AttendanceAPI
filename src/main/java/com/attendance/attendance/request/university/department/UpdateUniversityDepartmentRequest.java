package com.attendance.attendance.request.university.department;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "Update University Department Request", description = "Request to update an existing department's details in a university. Note: Department ID could not be added.")
public class UpdateUniversityDepartmentRequest {

    @NotBlank(message = "Department name is required")
    @Size(max = 100, message = "Department name cannot exceed 100 characters")
    @Schema(description = "Name of the department", example = "Computer Science")
    private String departmentName;
}