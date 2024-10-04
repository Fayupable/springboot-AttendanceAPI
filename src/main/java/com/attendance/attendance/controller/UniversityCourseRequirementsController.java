package com.attendance.attendance.controller;

import com.attendance.attendance.dto.CoursesRequirementsDto;
import com.attendance.attendance.entity.CoursesRequirements;
import com.attendance.attendance.request.university.course.requirement.AddCourseRequirementsRequest;
import com.attendance.attendance.request.university.course.requirement.UpdateCourseRequirementsRequest;
import com.attendance.attendance.response.ApiResponse;
import com.attendance.attendance.service.university.IUniversityCourseRequirementsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/university-course-requirements")
public class UniversityCourseRequirementsController {
    private final IUniversityCourseRequirementsService universityCourseRequirementsService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllUniversityCourseRequirements() {
        List<CoursesRequirements> universityCourses = universityCourseRequirementsService.getAllCourseRequirements();
        List<CoursesRequirementsDto> universityCourseDto = universityCourseRequirementsService.convertToDtoToList(universityCourses);
        return ResponseEntity.ok(new ApiResponse("University Courses retrieved successfully", universityCourseDto));
    }

    @GetMapping("/courseId/{courseId}")
    public ResponseEntity<ApiResponse> getUniversityCourseRequirementsByCourseId(@PathVariable Long courseId) {
        List<CoursesRequirements> universityCourses = universityCourseRequirementsService.getCourseRequirementsByCourseId(courseId);
        List<CoursesRequirementsDto> universityCourseDto = universityCourseRequirementsService.convertToDtoToList(universityCourses);
        return ResponseEntity.ok(new ApiResponse("University Courses retrieved successfully", universityCourseDto));
    }

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
