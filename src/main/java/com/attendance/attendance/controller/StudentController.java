package com.attendance.attendance.controller;

import com.attendance.attendance.dto.StudentDto;
import com.attendance.attendance.dto.UniversityCourseDto;
import com.attendance.attendance.entity.Student;
import com.attendance.attendance.entity.UniversityCourse;
import com.attendance.attendance.request.student.AddStudentRequest;
import com.attendance.attendance.request.student.UpdateStudentRequest;
import com.attendance.attendance.response.ApiResponse;
import com.attendance.attendance.service.student.IStudentService;
import com.attendance.attendance.service.university.IUniversityCourseService;
import com.attendance.attendance.service.university.IUniversityService;
import io.swagger.v3.oas.annotations.Operation;
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
    private final IUniversityCourseService universityService;

    @Operation(
            summary = "Get student by ID",
            description = "Retrieve student by their ID",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Student retrieved successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Student not found")
            }
    )
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

    @GetMapping("/{studentId}/courses")
    public ResponseEntity<ApiResponse> getCoursesForStudent(@PathVariable Long studentId) {
        try {
            List<UniversityCourse> courses = studentService.getCoursesForStudent(studentId);
            List<UniversityCourseDto> courseDto = universityService.convertToDtoToList(courses);
            return ResponseEntity.ok(new ApiResponse("Courses retrieved successfully", courseDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all students",
            description = "Retrieve all students",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Students retrieved successfully")
            }
    )
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

    @Operation(
            summary = "Get student by name",
            description = "Retrieve student by their name",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Students retrieved successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Student not found")
            }
    )
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

    @Operation(
            summary = "Get student by email",
            description = "Retrieve student by their email",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Students retrieved successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Student not found")
            }
    )
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

    @Operation(
            summary = "Add student",
            description = "Add a new student",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Student created successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "Conflict")
            }
    )
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

    @Operation(
            summary = "Update student",
            description = "Update an existing student",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Student updated successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "Conflict")
            }
    )
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

    @Operation(
            summary = "Delete student",
            description = "Delete a student by their ID",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Student deleted successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Student not found")
            }
    )
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