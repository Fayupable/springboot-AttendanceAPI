package com.attendance.attendance.request.student.courseRegistration;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Add Student Course Registration Request", description = "Request to register a student for a course, including student ID, course ID, and registration status")
public class AddStudentCourseRegistrationRequest {

    @Schema(description = "ID of the student", example = "123")
    private Long studentId;

    @Schema(description = "ID of the course", example = "456")
    private Long courseId;

    @Schema(description = "Registration status", example = "Registered")
    private String status;
}