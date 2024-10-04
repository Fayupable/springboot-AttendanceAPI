package com.attendance.attendance.controller;

import com.attendance.attendance.dto.TeacherDto;
import com.attendance.attendance.entity.Teacher;
import com.attendance.attendance.request.teacher.AddTeacherRequest;
import com.attendance.attendance.request.teacher.UpdateTeacherRequest;
import com.attendance.attendance.response.ApiResponse;
import com.attendance.attendance.service.teacher.ITeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/teacher")
public class TeacherController {
    private final ITeacherService teacherService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllTeachers() {
        List<Teacher> teachers = teacherService.getAllTeachers();
        List<TeacherDto> teacherDto = teacherService.convertToDtoList(teachers);
        return ResponseEntity.ok(new ApiResponse("Teacher retrieved successfully", teacherDto));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ApiResponse> getTeacherByEmail(@PathVariable String email) {
        List<Teacher> teachers = teacherService.getTeacherByEmail(email);
        List<TeacherDto> teacherDto = teacherService.convertToDtoList(teachers);
        return ResponseEntity.ok(new ApiResponse("Teacher retrieved successfully", teacherDto));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ApiResponse> getTeacherByName(@PathVariable String name) {
        List<Teacher> teachers = teacherService.getTeacherByName(name);
        List<TeacherDto> teacherDto = teacherService.convertToDtoList(teachers);
        return ResponseEntity.ok(new ApiResponse("Teacher retrieved successfully", teacherDto));
    }

    @GetMapping("/university/{universityId}")
    public ResponseEntity<ApiResponse> getTeacherByUniversityId(@PathVariable Long universityId) {
        List<Teacher> teachers = teacherService.getTeacherByUniversityId(universityId);
        List<TeacherDto> teacherDto = teacherService.convertToDtoList(teachers);
        return ResponseEntity.ok(new ApiResponse("Teacher retrieved successfully", teacherDto));
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<ApiResponse> getTeacherByDepartmentId(@PathVariable Long departmentId) {
        List<Teacher> teachers = teacherService.getTeacherByDepartmentId(departmentId);
        List<TeacherDto> teacherDto = teacherService.convertToDtoList(teachers);
        return ResponseEntity.ok(new ApiResponse("Teacher retrieved successfully", teacherDto));
    }

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
