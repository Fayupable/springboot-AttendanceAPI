package com.attendance.attendance.controller;

import com.attendance.attendance.dto.UniversityCourseDto;
import com.attendance.attendance.entity.UniversityCourse;
import com.attendance.attendance.request.university.course.course.AddUniversityCourseRequest;
import com.attendance.attendance.request.university.course.course.UpdateUniversityCourseRequest;
import com.attendance.attendance.response.ApiResponse;
import com.attendance.attendance.service.university.IUniversityCourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/university-course")
@Tag(name = "University Course")
public class UniversityCourseController {
    private final IUniversityCourseService universityCourseService;

    @Operation(
            summary = "Get all university courses",
            description = "Retrieve all university courses",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "University courses retrieved successfully")
            }
    )
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllUniversityCourses() {
        List<UniversityCourse> universityCourses = universityCourseService.getAllCourses();
        List<UniversityCourseDto> universityCourseDtos = universityCourseService.convertToDtoToList(universityCourses);
        return ResponseEntity.ok(new ApiResponse("University courses retrieved successfully", universityCourseDtos));
    }

    @Operation(
            summary = "Get university course by ID",
            description = "Retrieve university course by its ID",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "University course retrieved successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "University course not found")
            }
    )
    @GetMapping("/course/{courseId}")
    public ResponseEntity<ApiResponse> getUniversityCourseById(@PathVariable Long courseId) {
        try {
            UniversityCourse universityCourse = universityCourseService.getCourseById(courseId);
            UniversityCourseDto universityCourseDto = universityCourseService.convertToDto(universityCourse);
            return ResponseEntity.ok(new ApiResponse("University course retrieved successfully", universityCourseDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get university course by name",
            description = "Retrieve university course by its name",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "University courses retrieved successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "University course not found")
            }
    )
    @GetMapping("/name/{courseName}")
    public ResponseEntity<ApiResponse> getUniversityCourseByName(@PathVariable String courseName) {
        List<UniversityCourse> universityCourses = universityCourseService.getCourseByName(courseName);
        List<UniversityCourseDto> universityCourseDtos = universityCourseService.convertToDtoToList(universityCourses);
        return ResponseEntity.ok(new ApiResponse("University courses retrieved successfully", universityCourseDtos));
    }

    @Operation(
            summary = "Get university course by code",
            description = "Retrieve university course by its code",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "University courses retrieved successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "University course not found")
            }
    )
    @GetMapping("/code/{courseCode}")
    public ResponseEntity<ApiResponse> getUniversityCourseByCode(@PathVariable String courseCode) {
        List<UniversityCourse> universityCourses = universityCourseService.getCourseByCode(courseCode);
        List<UniversityCourseDto> universityCourseDto = universityCourseService.convertToDtoToList(universityCourses);
        return ResponseEntity.ok(new ApiResponse("University courses retrieved successfully", universityCourseDto));
    }

    @Operation(
            summary = "Add university course",
            description = "Add a new university course",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "University course created successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "Conflict")
            }
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addUniversityCourse(@RequestBody AddUniversityCourseRequest request) {
        try {
            UniversityCourse universityCourse = universityCourseService.addCourse(request);
            UniversityCourseDto universityCourseDto = universityCourseService.convertToDto(universityCourse);
            return ResponseEntity.ok(new ApiResponse("University course created successfully", universityCourseDto));
        } catch (Exception e) {
            return ResponseEntity.status(409).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @Operation(
            summary = "Update university course",
            description = "Update an existing university course",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "University course updated successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "Conflict")
            }
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{courseId}/department/{departmentId}")
    public ResponseEntity<ApiResponse> updateUniversityCourse(
            @RequestBody UpdateUniversityCourseRequest request,
            @PathVariable Long courseId,
            @PathVariable Long departmentId) {
        try {
            UniversityCourse universityCourse = universityCourseService.updateCourse(request, courseId, departmentId);
            UniversityCourseDto universityCourseDto = universityCourseService.convertToDto(universityCourse);
            return ResponseEntity.ok(new ApiResponse("University course updated successfully", universityCourseDto));
        } catch (Exception e) {
            return ResponseEntity.status(409).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @Operation(
            summary = "Delete university course",
            description = "Delete a university course by its ID",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "University course deleted successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "Conflict")
            }
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{courseId}")
    public ResponseEntity<ApiResponse> deleteUniversityCourse(@PathVariable Long courseId) {
        try {
            universityCourseService.deleteCourse(courseId);
            return ResponseEntity.ok(new ApiResponse("University course deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(409).body(new ApiResponse(e.getMessage(), null));
        }
    }
}