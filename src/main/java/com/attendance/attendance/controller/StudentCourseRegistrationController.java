package com.attendance.attendance.controller;


import com.attendance.attendance.dto.StudentCourseRegistrationDto;
import com.attendance.attendance.entity.StudentCourseRegistration;
import com.attendance.attendance.request.student.courseRegistration.AddStudentCourseRegistrationRequest;
import com.attendance.attendance.request.student.courseRegistration.UpdateStudentCourseRegistrationRequest;
import com.attendance.attendance.response.ApiResponse;
import com.attendance.attendance.service.student.courseRegistration.IStudentCourseRegistrationService;
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

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllStudentCourseRegistrations() {
        List<StudentCourseRegistration> studentCourseRegistrations = studentCourseRegistrationService.getAllStudentCourseRegistrations();
        List<StudentCourseRegistrationDto> studentCourseRegistrationDto = studentCourseRegistrationService.convertToDto(studentCourseRegistrations);
        return ResponseEntity.ok(new ApiResponse("Student course registration retrieved successfully", studentCourseRegistrationDto));
    }

    @GetMapping("/studentId/{studentId}")
    public ResponseEntity<ApiResponse> getStudentCourseRegistrationsByStudentId(@PathVariable Long studentId) {
        List<StudentCourseRegistration> studentCourseRegistrations = studentCourseRegistrationService.findByStudentId(studentId);
        List<StudentCourseRegistrationDto> studentCourseRegistrationDto = studentCourseRegistrationService.convertToDto(studentCourseRegistrations);
        return ResponseEntity.ok(new ApiResponse("Student course registration retrieved successfully", studentCourseRegistrationDto));
    }

    @GetMapping("/courseId/{courseId}")
    public ResponseEntity<ApiResponse> getStudentCourseRegistrationsByCourseId(@PathVariable Long courseId) {
        List<StudentCourseRegistration> studentCourseRegistrations = studentCourseRegistrationService.findByCourseId(courseId);
        List<StudentCourseRegistrationDto> studentCourseRegistrationDto = studentCourseRegistrationService.convertToDto(studentCourseRegistrations);
        return ResponseEntity.ok(new ApiResponse("Student course registration retrieved successfully", studentCourseRegistrationDto));
    }

    @GetMapping("/studentId/{studentId}/courseId/{courseId}")
    public ResponseEntity<ApiResponse> getStudentCourseRegistrationsByStudentIdAndCourseId(@PathVariable Long studentId, @PathVariable Long courseId) {
        StudentCourseRegistration studentCourseRegistration = studentCourseRegistrationService.findByStudentIdAndCourseId(studentId, courseId);
        StudentCourseRegistrationDto studentCourseRegistrationDto = studentCourseRegistrationService.convertToDto(studentCourseRegistration);
        return ResponseEntity.ok(new ApiResponse("Student course registration retrieved successfully", studentCourseRegistrationDto));
    }

    @GetMapping("/studentId/{studentId}/courseId/{courseId}/status/{status}")
    public ResponseEntity<ApiResponse> getStudentCourseRegistrationsByStudentIdAndCourseIdAndStatus(@PathVariable Long studentId, @PathVariable Long courseId, @PathVariable String status) {
        List<StudentCourseRegistration> studentCourseRegistrations = studentCourseRegistrationService.findByStudentIdAndCourseIdAndStatus(studentId, courseId, status);
        List<StudentCourseRegistrationDto> studentCourseRegistrationDto = studentCourseRegistrationService.convertToDto(studentCourseRegistrations);
        return ResponseEntity.ok(new ApiResponse("Student course registration retrieved successfully", studentCourseRegistrationDto));
    }

    @GetMapping("/studentId/{studentId}/status/{status}")
    public ResponseEntity<ApiResponse> getStudentCourseRegistrationsByStudentIdAndStatus(@PathVariable Long studentId, @PathVariable String status) {
        List<StudentCourseRegistration> studentCourseRegistrations = studentCourseRegistrationService.findByStudentIdAndStatus(studentId, status);
        List<StudentCourseRegistrationDto> studentCourseRegistrationDto = studentCourseRegistrationService.convertToDto(studentCourseRegistrations);
        return ResponseEntity.ok(new ApiResponse("Student course registration retrieved successfully", studentCourseRegistrationDto));
    }

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

    @PutMapping("/update-student-course-registration/{id}")
    @Transactional
    public ResponseEntity<ApiResponse> updateStudentCourseRegistration(@RequestBody UpdateStudentCourseRegistrationRequest request, @PathVariable Long id) {
        StudentCourseRegistration studentCourseRegistration = studentCourseRegistrationService.updateStudentCourseRegistration(request, id);
        StudentCourseRegistrationDto studentCourseRegistrationDto = studentCourseRegistrationService.convertToDto(studentCourseRegistration);
        return ResponseEntity.ok(new ApiResponse("Student course registration updated successfully", studentCourseRegistrationDto));
    }

    @DeleteMapping("/delete-student-course-registration/{id}")
    @Transactional
    public ResponseEntity<ApiResponse> deleteStudentCourseRegistration(@PathVariable Long id) {
        studentCourseRegistrationService.deleteStudentCourseRegistration(id);
        return ResponseEntity.ok(new ApiResponse("Student course registration deleted successfully", null));
    }


}
