package com.attendance.attendance.request.student;

import com.attendance.attendance.request.person.AddPersonRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Schema(name = "Add Student Request", description = "Request to add a new student, including personal details, student number, university ID, and department ID")
public class AddStudentRequest extends AddPersonRequest {

    @Schema(description = "Student number", example = "S123456")
    @NotBlank(message = "Student number is required")
    private String studentNumber;

    @Schema(description = "ID of the university", example = "1")
    @NotNull(message = "University ID is required")
    private Long universityId;

    @Schema(description = "ID of the department", example = "10")
    @NotNull(message = "Department ID is required")
    private Long departmentId;
}