package com.attendance.attendance.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class AddStudentRequest extends AddPersonRequest {

    @NotBlank(message = "Student number is required")
    private String studentNumber;

    @NotNull(message = "University ID is required")
    private Long universityId;

    @NotNull(message = "Department ID is required")
    private Long departmentId;


}
