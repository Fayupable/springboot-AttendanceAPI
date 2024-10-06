package com.attendance.attendance.request.university.course.attendance;

import com.attendance.attendance.enums.AttendanceStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(name = "Add Course Attendance Request", description = "Request to add attendance record for a specific course and student")
public class AddCourseAttendanceRequest {

    @Schema(description = "ID of the student", example = "12345")
    private Long studentId;

    @Schema(description = "ID of the course", example = "67890")
    private Long courseId;

    @Schema(description = "Date of the attendance", example = "2023-10-01")
    private LocalDate attendanceDate;

    @Schema(description = "Status of the attendance", example = "PRESENT")
    private AttendanceStatus attendanceStatus;
}