package com.attendance.attendance.controller;

import com.attendance.attendance.dto.CourseAttendanceDto;
import com.attendance.attendance.entity.CoursesAttendance;
import com.attendance.attendance.response.ApiResponse;
import com.attendance.attendance.service.university.IUniversityCourseAttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/course-attendance")
public class CourseAttendanceController {
    private final IUniversityCourseAttendanceService universityCourseAttendanceService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllCourseAttendance() {
        List<CoursesAttendance> courseAttendance = universityCourseAttendanceService.getAllCourseAttendance();
        List<CourseAttendanceDto> courseAttendanceDto = universityCourseAttendanceService.convertToDtoToList(courseAttendance);
        return ResponseEntity.ok(new ApiResponse("Course Attendance retrieved successfully", courseAttendanceDto));
    }

    @GetMapping("/courseAttendanceId/{courseAttendanceId}")
    public ResponseEntity<ApiResponse> getCourseAttendanceById(@PathVariable Long courseAttendanceId) {
        try {
            CoursesAttendance courseAttendance = universityCourseAttendanceService.getCourseAttendanceById(courseAttendanceId);
            CourseAttendanceDto courseAttendanceDto = universityCourseAttendanceService.convertToDto(courseAttendance);
            return ResponseEntity.ok(new ApiResponse("Course Attendance retrieved successfully", courseAttendanceDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/studentId/{studentId}/courseId/{courseId}/attendanceDate/{attendanceDate}")
    public ResponseEntity<ApiResponse> getCourseAttendanceByStudentIdAndCourseIdAndAttendanceDate(@PathVariable Long studentId, @PathVariable Long courseId, @PathVariable LocalDate attendanceDate) {
        try {
            List<CoursesAttendance> courseAttendance = universityCourseAttendanceService.getCourseAttendanceByStudentIdAndCourseIdAndAttendanceDate(studentId, courseId, attendanceDate);
            List<CourseAttendanceDto> courseAttendanceDto = universityCourseAttendanceService.convertToDtoToList(courseAttendance);
            return ResponseEntity.ok(new ApiResponse("Course Attendance retrieved successfully", courseAttendanceDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/studentId/{studentId}/courseId/{courseId}")
    public ResponseEntity<ApiResponse> getCourseAttendanceByStudentIdAndCourseId(@PathVariable Long studentId, @PathVariable Long courseId) {
        try {
            List<CoursesAttendance> courseAttendance = universityCourseAttendanceService.getCourseAttendanceByStudentIdAndCourseId(studentId, courseId);
            List<CourseAttendanceDto> courseAttendanceDto = universityCourseAttendanceService.convertToDtoToList(courseAttendance);
            return ResponseEntity.ok(new ApiResponse("Course Attendance retrieved successfully", courseAttendanceDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
        }
    }


    @GetMapping("/courseName/{courseName}")
    public ResponseEntity<ApiResponse> getCourseAttendanceByCourseNameContaining(@PathVariable String courseName) {
        try {
            List<CoursesAttendance> courseAttendance = universityCourseAttendanceService.getCourseAttendanceByCourseNameContaining(courseName);
            List<CourseAttendanceDto> courseAttendanceDto = universityCourseAttendanceService.convertToDtoToList(courseAttendance);
            return ResponseEntity.ok(new ApiResponse("Course Attendance retrieved successfully", courseAttendanceDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/courseId/{courseId}")
    public ResponseEntity<ApiResponse> getCourseAttendanceByCourseId(@PathVariable Long courseId) {
        try {
            List<CoursesAttendance> courseAttendance = universityCourseAttendanceService.getCourseAttendanceByCourseId(courseId);
            List<CourseAttendanceDto> courseAttendanceDto = universityCourseAttendanceService.convertToDtoToList(courseAttendance);
            return ResponseEntity.ok(new ApiResponse("Course Attendance retrieved successfully", courseAttendanceDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/studentId/{studentId}")
    public ResponseEntity<ApiResponse> getCourseAttendanceByStudentId(@PathVariable Long studentId) {
        try {
            List<CoursesAttendance> courseAttendance = universityCourseAttendanceService.getCourseAttendanceByStudentId(studentId);
            List<CourseAttendanceDto> courseAttendanceDto = universityCourseAttendanceService.convertToDtoToList(courseAttendance);
            return ResponseEntity.ok(new ApiResponse("Course Attendance retrieved successfully", courseAttendanceDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/studentId/{studentId}/courseName/{courseName}")
    public ResponseEntity<ApiResponse> getCourseAttendanceByStudentIdAndCourseNameContaining(@PathVariable Long studentId, @PathVariable String courseName) {
        try {
            List<CoursesAttendance> courseAttendance = universityCourseAttendanceService.getCourseAttendanceByStudentIdAndCourseNameContaining(studentId, courseName);
            List<CourseAttendanceDto> courseAttendanceDto = universityCourseAttendanceService.convertToDtoToList(courseAttendance);
            return ResponseEntity.ok(new ApiResponse("Course Attendance retrieved successfully", courseAttendanceDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
        }
    }




}
