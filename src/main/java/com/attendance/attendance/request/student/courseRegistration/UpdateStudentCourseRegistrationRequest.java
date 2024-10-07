package com.attendance.attendance.request.student.courseRegistration;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Schema(name = "Update Student Course Registration Request", description = "Request to update a student's course registration, including student ID, course ID, registration date, and status")
public class UpdateStudentCourseRegistrationRequest {

    @Schema(description = "ID of the student", example = "123")
    private Long studentId;

    @Schema(description = "ID of the course", example = "456")
    private Long courseId;

    @Schema(description = "Date of registration", example = "2023-10-01T12:00:00")
    private LocalDateTime registrationDate;

    @Schema(description = "Registration status", example = "PENDING")
    private String status;
}