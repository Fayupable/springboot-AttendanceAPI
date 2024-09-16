package com.attendance.attendance.controller;

import com.attendance.attendance.dto.StudentDto;
import com.attendance.attendance.entity.Student;
import com.attendance.attendance.request.student.AddStudentRequest;
import com.attendance.attendance.response.ApiResponse;
import com.attendance.attendance.service.student.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/student")
public class StudentController {
    private final IStudentService studentService;

    @PostMapping("/add")
    @Transactional
    public ResponseEntity<ApiResponse> createStudent(@RequestBody AddStudentRequest request) {
        try {
            Student createdStudent = studentService.addStudent(request);
            StudentDto studentDto = studentService.convertToDto(createdStudent);
            return ResponseEntity.ok(new ApiResponse("Student created successfully", studentDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(409).body(new ApiResponse(e.getMessage(), null));
        }
    }

}
