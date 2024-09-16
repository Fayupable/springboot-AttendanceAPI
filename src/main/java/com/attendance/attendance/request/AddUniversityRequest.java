package com.attendance.attendance.request;

import com.attendance.attendance.entity.UniversityDepartment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class AddUniversityRequest {

    @NotBlank(message = "University name is required")
    @Size(max = 100, message = "University name cannot exceed 100 characters")
    private String universityName;

    @NotBlank(message = "Location is required")
    @Size(max = 100, message = "Location cannot exceed 100 characters")
    private String location;

    private List<AddUniversityDepartmentRequest> departments;


}

