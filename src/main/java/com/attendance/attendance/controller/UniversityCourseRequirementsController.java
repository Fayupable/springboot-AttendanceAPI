package com.attendance.attendance.controller;

import com.attendance.attendance.dto.CoursesRequirementsDto;
import com.attendance.attendance.entity.CoursesRequirements;
import com.attendance.attendance.request.university.course.requirement.AddCourseRequirementsRequest;
import com.attendance.attendance.request.university.course.requirement.UpdateCourseRequirementsRequest;
import com.attendance.attendance.response.ApiResponse;
import com.attendance.attendance.service.university.IUniversityCourseRequirementsService;
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
@RequestMapping("${api.prefix}/university-course-requirements")
@Tag(name = "University Course Requirements")
public class UniversityCourseRequirementsController {
    private final IUniversityCourseRequirementsService universityCourseRequirementsService;

    @Operation(
            summary = "Get all university course requirements",
            description = "Retrieve all university course requirements",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "University Courses retrieved successfully")
            }
    )
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllUniversityCourseRequirements() {
        List<CoursesRequirements> universityCourses = universityCourseRequirementsService.getAllCourseRequirements();
        List<CoursesRequirementsDto> universityCourseDto = universityCourseRequirementsService.convertToDtoToList(universityCourses);
        return ResponseEntity.ok(new ApiResponse("University Courses retrieved successfully", universityCourseDto));
    }

    @Operation(
            summary = "Get university course requirements by course ID",
            description = "Retrieve university course requirements by course ID",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "University Courses retrieved successfully")
            }
    )
    @GetMapping("/courseId/{courseId}")
    public ResponseEntity<ApiResponse> getUniversityCourseRequirementsByCourseId(@PathVariable Long courseId) {
        List<CoursesRequirements> universityCourses = universityCourseRequirementsService.getCourseRequirementsByCourseId(courseId);
        List<CoursesRequirementsDto> universityCourseDto = universityCourseRequirementsService.convertToDtoToList(universityCourses);
        return ResponseEntity.ok(new ApiResponse("University Courses retrieved successfully", universityCourseDto));
    }

    @Operation(
            summary = "Get university course requirements by ID",
            description = "Retrieve university course requirements by ID",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "University Course retrieved successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "University Course not found")
            }
    )
    @GetMapping("/courseRequirementsId/{courseRequirementsId}")
    public ResponseEntity<ApiResponse> getUniversityCourseRequirementsById(@PathVariable Long courseRequirementsId) {
        try {
            CoursesRequirements universityCourse = universityCourseRequirementsService.getCourseRequirementsById(courseRequirementsId);
            CoursesRequirementsDto universityCourseDto = universityCourseRequirementsService.convertToDto(universityCourse);
            return ResponseEntity.ok(new ApiResponse("University Course retrieved successfully", universityCourseDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @Operation(
            summary = "Add university course requirements",
            description = "Add new university course requirements",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "University Course added successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request")
            }
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add-course-requirements")
    @Transactional
    public ResponseEntity<ApiResponse> addUniversityCourseRequirements(@RequestBody AddCourseRequirementsRequest courseRequirements) {
        try {
            CoursesRequirements universityCourse = universityCourseRequirementsService.addCourseRequirements(courseRequirements);
            CoursesRequirementsDto universityCourseDto = universityCourseRequirementsService.convertToDto(universityCourse);
            return ResponseEntity.ok(new ApiResponse("University Course added successfully", universityCourseDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @Operation(
            summary = "Update university course requirements",
            description = "Update existing university course requirements",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "University Course updated successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request")
            }
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update-course-requirements/{id}/course/{courseId}")
    @Transactional
    public ResponseEntity<ApiResponse> updateUniversityCourseRequirements(
            @RequestBody UpdateCourseRequirementsRequest courseRequirements,
            @PathVariable Long id,
            @PathVariable Long courseId) {
        try {
            CoursesRequirements universityCourse = universityCourseRequirementsService.updateCourseRequirements(courseRequirements, id, courseId);
            CoursesRequirementsDto universityCourseDto = universityCourseRequirementsService.convertToDto(universityCourse);
            return ResponseEntity.ok(new ApiResponse("University Course updated successfully", universityCourseDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @Operation(
            summary = "Delete university course requirements",
            description = "Delete university course requirements by ID",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "University Course deleted successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request")
            }
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete-course-requirements/{id}")
    @Transactional
    public ResponseEntity<ApiResponse> deleteUniversityCourseRequirements(@PathVariable Long id) {
        try {
            universityCourseRequirementsService.deleteCourseRequirements(id);
            return ResponseEntity.ok(new ApiResponse("University Course deleted successfully", null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(new ApiResponse(e.getMessage(), null));
        }
    }
}