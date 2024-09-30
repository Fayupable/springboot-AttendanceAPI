package com.attendance.attendance.controller;


import com.attendance.attendance.dto.StudentCourseRegistrationDto;
import com.attendance.attendance.entity.StudentCourseRegistration;
import com.attendance.attendance.response.ApiResponse;
import com.attendance.attendance.service.student.courseRegistration.IStudentCourseRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
