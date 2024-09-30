package com.attendance.attendance.dto;

import com.attendance.attendance.enums.AttendanceStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CourseAttendanceDto {

    private String studentName;

    private String courseName;

    private LocalDate attendanceDate;

    private AttendanceStatus attendanceStatus;
}
