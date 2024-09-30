package com.attendance.attendance.request.university.course.attendance;

import com.attendance.attendance.enums.AttendanceStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateCourseAttendanceRequest {
    private Long studentId;
    private Long courseId;
    private LocalDate attendanceDate;
    private AttendanceStatus attendanceStatus;
}
