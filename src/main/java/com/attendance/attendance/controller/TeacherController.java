package com.attendance.attendance.controller;

import com.attendance.attendance.dto.TeacherDto;
import com.attendance.attendance.entity.Teacher;
import com.attendance.attendance.request.teacher.AddTeacherRequest;
import com.attendance.attendance.request.teacher.UpdateTeacherRequest;
import com.attendance.attendance.response.ApiResponse;
import com.attendance.attendance.service.teacher.ITeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/teacher")
@Tag(name = "Teacher")
public class TeacherController {
    private final ITeacherService teacherService;

    @Operation(
            summary = "Get all teachers",
            description = "Retrieve all teachers",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Teachers retrieved successfully")
            }
    )
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllTeachers() {
        List<Teacher> teachers = teacherService.getAllTeachers();
        List<TeacherDto> teacherDto = teacherService.convertToDtoList(teachers);
        return ResponseEntity.ok(new ApiResponse("Teachers retrieved successfully", teacherDto));
    }

    @Operation(
            summary = "Get teacher by email",
            description = "Retrieve teacher by their email",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Teacher retrieved successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Teacher not found")
            }
    )
    @GetMapping("/email/{email}")
    public ResponseEntity<ApiResponse> getTeacherByEmail(@PathVariable String email) {
        List<Teacher> teachers = teacherService.getTeacherByEmail(email);
        List<TeacherDto> teacherDto = teacherService.convertToDtoList(teachers);
        return ResponseEntity.ok(new ApiResponse("Teacher retrieved successfully", teacherDto));
    }

    @Operation(
            summary = "Get teacher by name",
            description = "Retrieve teacher by their name",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Teacher retrieved successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Teacher not found")
            }
    )
    @GetMapping("/name/{name}")
    public ResponseEntity<ApiResponse> getTeacherByName(@PathVariable String name) {
        List<Teacher> teachers = teacherService.getTeacherByName(name);
        List<TeacherDto> teacherDto = teacherService.convertToDtoList(teachers);
        return ResponseEntity.ok(new ApiResponse("Teacher retrieved successfully", teacherDto));
    }

    @Operation(
            summary = "Get teacher by university ID",
            description = "Retrieve teacher by their university ID",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Teacher retrieved successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Teacher not found")
            }
    )
    @GetMapping("/university/{universityId}")
    public ResponseEntity<ApiResponse> getTeacherByUniversityId(@PathVariable Long universityId) {
        List<Teacher> teachers = teacherService.getTeacherByUniversityId(universityId);
        List<TeacherDto> teacherDto = teacherService.convertToDtoList(teachers);
        return ResponseEntity.ok(new ApiResponse("Teacher retrieved successfully", teacherDto));
    }

    @Operation(
            summary = "Get teacher by department ID",
            description = "Retrieve teacher by their department ID",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Teacher retrieved successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Teacher not found")
            }
    )
    @GetMapping("/department/{departmentId}")
    public ResponseEntity<ApiResponse> getTeacherByDepartmentId(@PathVariable Long departmentId) {
        List<Teacher> teachers = teacherService.getTeacherByDepartmentId(departmentId);
        List<TeacherDto> teacherDto = teacherService.convertToDtoList(teachers);
        return ResponseEntity.ok(new ApiResponse("Teacher retrieved successfully", teacherDto));
    }

    @Operation(
            summary = "Add teacher",
            description = "Add a new teacher",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Teacher created successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "Conflict")
            }
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    @Transactional
    public ResponseEntity<ApiResponse> createTeacher(@RequestBody AddTeacherRequest request) {
        try {
            Teacher createdTeacher = teacherService.addTeacher(request);
            TeacherDto teacherDto = teacherService.convertToDto(createdTeacher);
            return ResponseEntity.ok(new ApiResponse("Teacher created successfully", teacherDto));
        } catch (Exception e) {
            return ResponseEntity.status(409).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @Operation(
            summary = "Delete teacher",
            description = "Delete a teacher by their ID",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Teacher deleted successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Teacher not found")
            }
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{teacherId}")
    @Transactional
    public ResponseEntity<ApiResponse> deleteTeacher(@PathVariable Long teacherId) {
        try {
            teacherService.deleteTeacher(teacherId);
            return ResponseEntity.ok(new ApiResponse("Teacher deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @Operation(
            summary = "Update teacher",
            description = "Update an existing teacher",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Teacher updated successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Teacher not found")
            }
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{teacherId}")
    @Transactional
    public ResponseEntity<ApiResponse> updateTeacher(@RequestBody UpdateTeacherRequest request, @PathVariable Long teacherId) {
        try {
            Teacher updatedTeacher = teacherService.updateTeacher(request, teacherId);
            TeacherDto teacherDto = teacherService.convertToDto(updatedTeacher);
            return ResponseEntity.ok(new ApiResponse("Teacher updated successfully", teacherDto));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
        }
    }
}