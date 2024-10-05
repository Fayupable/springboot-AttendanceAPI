package com.attendance.attendance.controller;

import com.attendance.attendance.dto.CourseAttendanceDto;
import com.attendance.attendance.entity.CoursesAttendance;
import com.attendance.attendance.request.university.course.attendance.AddCourseAttendanceRequest;
import com.attendance.attendance.request.university.course.attendance.UpdateCourseAttendanceRequest;
import com.attendance.attendance.response.ApiResponse;
import com.attendance.attendance.service.university.IUniversityCourseAttendanceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/course-attendance")
@Tag(name = "Course Attendance")
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

    @PostMapping("/add")
    @Transactional
    public ResponseEntity<ApiResponse> addCourseAttendance(@RequestBody AddCourseAttendanceRequest courseAttendance) {
        try {
            CoursesAttendance newCourseAttendance = universityCourseAttendanceService.addCourseAttendance(courseAttendance);
            CourseAttendanceDto courseAttendanceDto = universityCourseAttendanceService.convertToDto(newCourseAttendance);
            return ResponseEntity.ok(new ApiResponse("Course Attendance added successfully", courseAttendanceDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/update-course-attendance/{courseAttendanceId}")
    @Transactional
    public ResponseEntity<ApiResponse> updateCourseAttendance(@RequestBody UpdateCourseAttendanceRequest courseAttendance, @PathVariable Long courseAttendanceId) {
        try {
            CoursesAttendance updatedCourseAttendance = universityCourseAttendanceService.updateCourseAttendance(courseAttendance, courseAttendanceId);
            CourseAttendanceDto courseAttendanceDto = universityCourseAttendanceService.convertToDto(updatedCourseAttendance);
            return ResponseEntity.ok(new ApiResponse("Course Attendance updated successfully", courseAttendanceDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/delete-course-attendance/{courseAttendanceId}")
    @Transactional
    public ResponseEntity<ApiResponse> deleteCourseAttendance(@PathVariable Long courseAttendanceId) {
        try {
            universityCourseAttendanceService.deleteCourseAttendance(courseAttendanceId);
            return ResponseEntity.ok(new ApiResponse("Course Attendance deleted successfully", null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(new ApiResponse(e.getMessage(), null));
        }
    }


}
