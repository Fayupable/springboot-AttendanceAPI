package com.attendance.attendance.controller;

import com.attendance.attendance.dto.StudentCourseRegistrationDto;
import com.attendance.attendance.entity.StudentCourseRegistration;
import com.attendance.attendance.request.student.courseRegistration.AddStudentCourseRegistrationRequest;
import com.attendance.attendance.request.student.courseRegistration.UpdateStudentCourseRegistrationRequest;
import com.attendance.attendance.response.ApiResponse;
import com.attendance.attendance.service.student.courseRegistration.IStudentCourseRegistrationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/student/courseRegistration")
@Tag(name = "Student Course Registration")
public class StudentCourseRegistrationController {
    private final IStudentCourseRegistrationService studentCourseRegistrationService;

    @Operation(
            summary = "Get all student course registrations",
            description = "Retrieve all student course registrations",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Student course registration retrieved successfully")
            }
    )
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllStudentCourseRegistrations() {
        List<StudentCourseRegistration> studentCourseRegistrations = studentCourseRegistrationService.getAllStudentCourseRegistrations();
        List<StudentCourseRegistrationDto> studentCourseRegistrationDto = studentCourseRegistrationService.convertToDto(studentCourseRegistrations);
        return ResponseEntity.ok(new ApiResponse("Student course registration retrieved successfully", studentCourseRegistrationDto));
    }

    @Operation(
            summary = "Get student course registrations by student ID",
            description = "Retrieve student course registrations by student ID",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Student course registration retrieved successfully")
            }
    )
    @GetMapping("/studentId/{studentId}")
    public ResponseEntity<ApiResponse> getStudentCourseRegistrationsByStudentId(@PathVariable Long studentId) {
        List<StudentCourseRegistration> studentCourseRegistrations = studentCourseRegistrationService.findByStudentId(studentId);
        List<StudentCourseRegistrationDto> studentCourseRegistrationDto = studentCourseRegistrationService.convertToDto(studentCourseRegistrations);
        return ResponseEntity.ok(new ApiResponse("Student course registration retrieved successfully", studentCourseRegistrationDto));
    }

    @Operation(
            summary = "Get student course registrations by course ID",
            description = "Retrieve student course registrations by course ID",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Student course registration retrieved successfully")
            }
    )
    @GetMapping("/courseId/{courseId}")
    public ResponseEntity<ApiResponse> getStudentCourseRegistrationsByCourseId(@PathVariable Long courseId) {
        List<StudentCourseRegistration> studentCourseRegistrations = studentCourseRegistrationService.findByCourseId(courseId);
        List<StudentCourseRegistrationDto> studentCourseRegistrationDto = studentCourseRegistrationService.convertToDto(studentCourseRegistrations);
        return ResponseEntity.ok(new ApiResponse("Student course registration retrieved successfully", studentCourseRegistrationDto));
    }

    @Operation(
            summary = "Get student course registrations by student ID and course ID",
            description = "Retrieve student course registrations by student ID and course ID",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Student course registration retrieved successfully")
            }
    )
    @GetMapping("/studentId/{studentId}/courseId/{courseId}")
    public ResponseEntity<ApiResponse> getStudentCourseRegistrationsByStudentIdAndCourseId(@PathVariable Long studentId, @PathVariable Long courseId) {
        StudentCourseRegistration studentCourseRegistration = studentCourseRegistrationService.findByStudentIdAndCourseId(studentId, courseId);
        StudentCourseRegistrationDto studentCourseRegistrationDto = studentCourseRegistrationService.convertToDto(studentCourseRegistration);
        return ResponseEntity.ok(new ApiResponse("Student course registration retrieved successfully", studentCourseRegistrationDto));
    }

    @Operation(
            summary = "Get student course registrations by student ID, course ID, and status",
            description = "Retrieve student course registrations by student ID, course ID, and status",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Student course registration retrieved successfully")
            }
    )
    @GetMapping("/studentId/{studentId}/courseId/{courseId}/status/{status}")
    public ResponseEntity<ApiResponse> getStudentCourseRegistrationsByStudentIdAndCourseIdAndStatus(@PathVariable Long studentId, @PathVariable Long courseId, @PathVariable String status) {
        List<StudentCourseRegistration> studentCourseRegistrations = studentCourseRegistrationService.findByStudentIdAndCourseIdAndStatus(studentId, courseId, status);
        List<StudentCourseRegistrationDto> studentCourseRegistrationDto = studentCourseRegistrationService.convertToDto(studentCourseRegistrations);
        return ResponseEntity.ok(new ApiResponse("Student course registration retrieved successfully", studentCourseRegistrationDto));
    }

    @Operation(
            summary = "Get student course registrations by student ID and status",
            description = "Retrieve student course registrations by student ID and status",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Student course registration retrieved successfully")
            }
    )
    @GetMapping("/studentId/{studentId}/status/{status}")
    public ResponseEntity<ApiResponse> getStudentCourseRegistrationsByStudentIdAndStatus(@PathVariable Long studentId, @PathVariable String status) {
        List<StudentCourseRegistration> studentCourseRegistrations = studentCourseRegistrationService.findByStudentIdAndStatus(studentId, status);
        List<StudentCourseRegistrationDto> studentCourseRegistrationDto = studentCourseRegistrationService.convertToDto(studentCourseRegistrations);
        return ResponseEntity.ok(new ApiResponse("Student course registration retrieved successfully", studentCourseRegistrationDto));
    }

    @Operation(
            summary = "Add student course registration",
            description = "Add a new student course registration",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Student course registration created successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request")
            }
    )
    @PostMapping("/add-student-course-registration")
    @Transactional
    public ResponseEntity<ApiResponse> addStudentCourseRegistration(@RequestBody AddStudentCourseRegistrationRequest request) {
        try {
            StudentCourseRegistration studentCourseRegistration = studentCourseRegistrationService.addStudentCourseRegistration(request);
            StudentCourseRegistrationDto studentCourseRegistrationDto = studentCourseRegistrationService.convertToDto(studentCourseRegistration);
            return ResponseEntity.ok(new ApiResponse("Student course registration created successfully", studentCourseRegistrationDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @Operation(
            summary = "Update student course registration",
            description = "Update an existing student course registration",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Student course registration updated successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request")
            }
    )
    @PutMapping("/update-student-course-registration/{id}")
    @Transactional
    public ResponseEntity<ApiResponse> updateStudentCourseRegistration(@RequestBody UpdateStudentCourseRegistrationRequest request, @PathVariable Long id) {
        StudentCourseRegistration studentCourseRegistration = studentCourseRegistrationService.updateStudentCourseRegistration(request, id);

        StudentCourseRegistrationDto studentCourseRegistrationDto = studentCourseRegistrationService.convertToDto(studentCourseRegistration);
        return ResponseEntity.ok(new ApiResponse("Student course registration updated successfully", studentCourseRegistrationDto));
    }

    @Operation(
            summary = "Delete student course registration",
            description = "Delete a student course registration by ID",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Student course registration deleted successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request")
            }
    )
    @DeleteMapping("/delete-student-course-registration/{id}")
    @Transactional
    public ResponseEntity<ApiResponse> deleteStudentCourseRegistration(@PathVariable Long id) {
        studentCourseRegistrationService.deleteStudentCourseRegistration(id);
        return ResponseEntity.ok(new ApiResponse("Student course registration deleted successfully", null));
    }
}