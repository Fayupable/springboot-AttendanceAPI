package com.attendance.attendance.controller;

import com.attendance.attendance.dto.CourseAttendanceDto;
import com.attendance.attendance.entity.CoursesAttendance;
import com.attendance.attendance.request.university.course.attendance.AddCourseAttendanceRequest;
import com.attendance.attendance.request.university.course.attendance.UpdateCourseAttendanceRequest;
import com.attendance.attendance.response.ApiResponse;
import com.attendance.attendance.service.university.IUniversityCourseAttendanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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

    @Operation(
            summary = "Get all course attendance",
            description = "Retrieve all course attendance records",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Course Attendance retrieved successfully")
            }
    )
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllCourseAttendance() {
        List<CoursesAttendance> courseAttendance = universityCourseAttendanceService.getAllCourseAttendance();
        List<CourseAttendanceDto> courseAttendanceDto = universityCourseAttendanceService.convertToDtoToList(courseAttendance);
        return ResponseEntity.ok(new ApiResponse("Course Attendance retrieved successfully", courseAttendanceDto));
    }

    @Operation(
            summary = "Get course attendance by ID",
            description = "Retrieve course attendance by its ID",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Course Attendance retrieved successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Course Attendance not found")
            }
    )
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

    @Operation(
            summary = "Get course attendance by student ID, course ID, and attendance date",
            description = "Retrieve course attendance by student ID, course ID, and attendance date",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Course Attendance retrieved successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Course Attendance not found")
            }
    )
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

    @Operation(
            summary = "Get course attendance by student ID and course ID",
            description = "Retrieve course attendance by student ID and course ID",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Course Attendance retrieved successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Course Attendance not found")
            }
    )
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

    @Operation(
            summary = "Get course attendance by course name",
            description = "Retrieve course attendance by course name",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Course Attendance retrieved successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Course Attendance not found")
            }
    )
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

    @Operation(
            summary = "Get course attendance by course ID",
            description = "Retrieve course attendance by course ID",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Course Attendance retrieved successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Course Attendance not found")
            }
    )
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

    @Operation(
            summary = "Get course attendance by student ID",
            description = "Retrieve course attendance by student ID",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Course Attendance retrieved successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Course Attendance not found")
            }
    )
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

    @Operation(
            summary = "Get course attendance by student ID and course name",
            description = "Retrieve course attendance by student ID and course name",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Course Attendance retrieved successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Course Attendance not found")
            }
    )
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

    @Operation(
            summary = "Add course attendance",
            description = "Add a new course attendance record",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Course Attendance added successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request")
            }
    )
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

    @Operation(
            summary = "Update course attendance",
            description = "Update an existing course attendance record",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Course Attendance updated successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request")
            }
    )
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

    @Operation(
            summary = "Delete course attendance",
            description = "Delete a course attendance record by its ID",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Course Attendance deleted successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request")
            }
    )
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