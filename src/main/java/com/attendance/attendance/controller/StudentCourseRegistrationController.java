package com.attendance.attendance.controller;


import com.attendance.attendance.dto.StudentCourseRegistrationDto;
import com.attendance.attendance.entity.StudentCourseRegistration;
import com.attendance.attendance.request.student.courseRegistration.AddStudentCourseRegistrationRequest;
import com.attendance.attendance.request.student.courseRegistration.UpdateStudentCourseRegistrationRequest;
import com.attendance.attendance.response.ApiResponse;
import com.attendance.attendance.service.student.courseRegistration.IStudentCourseRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/student/courseRegistration")
public class StudentCourseRegistrationController {
    private final IStudentCourseRegistrationService studentCourseRegistrationService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllStudentCourseRegistrations() {
        List<StudentCourseRegistration> studentCourseRegistrations = studentCourseRegistrationService.getAllStudentCourseRegistrations();
        List<StudentCourseRegistrationDto> studentCourseRegistrationDto = studentCourseRegistrationService.convertToDto(studentCourseRegistrations);
        return ResponseEntity.ok(new ApiResponse("Student course registration retrieved successfully", studentCourseRegistrationDto));
    }

    @PostMapping("/add-student-course-registration")
    @Transactional
    public ResponseEntity<ApiResponse> createStudentCourseRegistration(AddStudentCourseRegistrationRequest request) {
        StudentCourseRegistration studentCourseRegistration = studentCourseRegistrationService.addStudentCourseRegistration(request);
        StudentCourseRegistrationDto studentCourseRegistrationDto = studentCourseRegistrationService.convertToDto(studentCourseRegistration);
        return ResponseEntity.ok(new ApiResponse("Student course registration created successfully", studentCourseRegistrationDto));
    }

    @PutMapping("/update-student-course-registration/{id}")
    @Transactional
    public ResponseEntity<ApiResponse> updateStudentCourseRegistration(UpdateStudentCourseRegistrationRequest request, @PathVariable Long id) {
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
