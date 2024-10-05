package com.attendance.attendance.controller;

import com.attendance.attendance.dto.StudentDto;
import com.attendance.attendance.entity.Student;
import com.attendance.attendance.request.student.AddStudentRequest;
import com.attendance.attendance.request.student.UpdateStudentRequest;
import com.attendance.attendance.response.ApiResponse;
import com.attendance.attendance.service.student.IStudentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/student")
@Tag(name = "Student")
public class StudentController {
    private final IStudentService studentService;

    @GetMapping("/{studentId}/student")
    public ResponseEntity<ApiResponse> getStudentById(@PathVariable Long studentId) {
        try {
            Student student = studentService.getStudentById(studentId);
            StudentDto studentDto = studentService.convertToDto(student);
            return ResponseEntity.ok(new ApiResponse("Student retrieved successfully", studentDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllStudents() {
        try {
            List<Student> students = studentService.getAllStudents();
            List<StudentDto> studentDto = studentService.convertToDtoList(students);
            return ResponseEntity.ok(new ApiResponse("Students retrieved successfully", studentDto));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/studentName/{studentName}")
    public ResponseEntity<ApiResponse> getStudentByName(@PathVariable String studentName) {
        try {
            List<Student> students = studentService.getStudentByName(studentName);
            List<StudentDto> studentDto = studentService.convertToDtoList(students);
            return ResponseEntity.ok(new ApiResponse("Students retrieved successfully", studentDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/studentEmail/{studentEmail}")
    public ResponseEntity<ApiResponse> getStudentByEmail(@PathVariable String studentEmail) {
        try {
            List<Student> students = studentService.getStudentByEmail(studentEmail);
            List<StudentDto> studentDto = studentService.convertToDtoList(students);
            return ResponseEntity.ok(new ApiResponse("Students retrieved successfully", studentDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
        }
    }


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

    @PutMapping("/{studentId}/update")
    @Transactional
    public ResponseEntity<ApiResponse> updateStudent(@RequestBody UpdateStudentRequest request, @PathVariable Long studentId) {
        try {
            Student updatedStudent = studentService.updateStudent(request, studentId);
            StudentDto studentDto = studentService.convertToDto(updatedStudent);
            return ResponseEntity.ok(new ApiResponse("Student updated successfully", studentDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(409).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/{studentId}/delete")
    public ResponseEntity<ApiResponse> deleteStudent(@PathVariable Long studentId) {
        try {
            studentService.deleteStudent(studentId);
            return ResponseEntity.ok(new ApiResponse("Student deleted successfully", null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
        }
    }



}
