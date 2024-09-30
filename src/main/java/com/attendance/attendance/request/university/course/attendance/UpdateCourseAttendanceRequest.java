package com.attendance.attendance.request.university.course.attendance;

import lombok.Data;

@Data
public class UpdateCourseAttendanceRequest {
    private String studentName;
    private String courseName;
    private String attendanceDate;
    private String attendanceStatus;
}
