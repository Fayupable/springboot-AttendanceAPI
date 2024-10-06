package com.attendance.attendance.request.university.university;

import com.attendance.attendance.request.university.department.AddUniversityDepartmentRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
@Schema(name = "Update University Request", description = "Request to update an existing university, including university name, location, and associated departments")
public class UpdateUniversityRequest {

    @NotBlank(message = "University name is required")
    @Size(max = 100, message = "University name cannot exceed 100 characters")
    @Schema(description = "Name of the university", example = "Harvard University")
    private String universityName;

    @NotBlank(message = "Location is required")
    @Size(max = 100, message = "Location cannot exceed 100 characters")
    @Schema(description = "Location of the university", example = "Cambridge, MA")
    private String location;

    @Schema(description = "List of departments associated with the university")
    private List<AddUniversityDepartmentRequest> departments;
}