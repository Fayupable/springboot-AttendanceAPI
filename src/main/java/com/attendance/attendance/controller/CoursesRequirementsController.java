package com.attendance.attendance.controller;

import com.attendance.attendance.dto.CoursesRequirementsDto;
import com.attendance.attendance.entity.CoursesRequirements;
import com.attendance.attendance.request.university.course.requirement.AddCourseRequirementsRequest;
import com.attendance.attendance.request.university.course.requirement.UpdateCourseRequirementsRequest;
import com.attendance.attendance.response.ApiResponse;
import com.attendance.attendance.service.university.ICourseRequirementsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/courses-requirements")
public class CoursesRequirementsController {
    private final ICourseRequirementsService courseRequirementsService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllCoursesRequirements() {
        List<CoursesRequirements> coursesRequirements = courseRequirementsService.getAllCourseRequirements();
        List<CoursesRequirementsDto> coursesRequirementsDto = coursesRequirements.stream().map(courseRequirementsService::convertToDto).collect(Collectors.toList());
        return ResponseEntity.ok(new ApiResponse("Courses Requirements retrieved successfully", coursesRequirementsDto));
    }

    @GetMapping("/courseId/{courseId}")
    public ResponseEntity<ApiResponse> getCourseRequirementsByCourseId(@PathVariable Long courseId) {
        try {
            CoursesRequirements coursesRequirements = courseRequirementsService.getCourseRequirementsByCourseId(courseId);
            CoursesRequirementsDto coursesRequirementsDto = courseRequirementsService.convertToDto(coursesRequirements);
            return ResponseEntity.ok(new ApiResponse("Courses Requirements retrieved successfully", coursesRequirementsDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/courseName/{courseName}")
    public ResponseEntity<ApiResponse> getCourseRequirementsByCourseName(@PathVariable String courseName) {
        try {
            List<CoursesRequirements> coursesRequirements = courseRequirementsService.getCourseRequirementsByCourseName(courseName);
            List<CoursesRequirementsDto> coursesRequirementsDto = coursesRequirements.stream().map(courseRequirementsService::convertToDto).collect(Collectors.toList());
            return ResponseEntity.ok(new ApiResponse("Courses Requirements retrieved successfully", coursesRequirementsDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/courseRequirementsId/{courseRequirementsId}")
    public ResponseEntity<ApiResponse> getCourseRequirementsById(@PathVariable Long courseRequirementsId) {
        try {
            CoursesRequirements coursesRequirements = courseRequirementsService.getCourseRequirementsById(courseRequirementsId);
            CoursesRequirementsDto coursesRequirementsDto = courseRequirementsService.convertToDto(coursesRequirements);
            return ResponseEntity.ok(new ApiResponse("Courses Requirements retrieved successfully", coursesRequirementsDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping("/add")
    @Transactional
    public ResponseEntity<ApiResponse> addCourseRequirements(@RequestBody AddCourseRequirementsRequest request) {
        try {
            CoursesRequirements coursesRequirements = courseRequirementsService.addCourseRequirements(request);
            CoursesRequirementsDto coursesRequirementsDto = courseRequirementsService.convertToDto(coursesRequirements);
            return ResponseEntity.ok(new ApiResponse("Courses Requirements added successfully", coursesRequirementsDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/update/{courseRequirementsId}")
    @Transactional
    public ResponseEntity<ApiResponse> updateCourseRequirements(@RequestBody UpdateCourseRequirementsRequest request, @PathVariable Long courseRequirementsId) {
        try {
            CoursesRequirements coursesRequirements = courseRequirementsService.updateCourseRequirements(request, courseRequirementsId);
            CoursesRequirementsDto coursesRequirementsDto = courseRequirementsService.convertToDto(coursesRequirements);
            return ResponseEntity.ok(new ApiResponse("Courses Requirements updated successfully", coursesRequirementsDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/delete/{courseRequirementsId}")
    public ResponseEntity<ApiResponse> deleteCourseRequirements(@PathVariable Long courseRequirementsId) {
        try {
            courseRequirementsService.deleteCourseRequirements(courseRequirementsId);
            return ResponseEntity.ok(new ApiResponse("Courses Requirements deleted successfully", null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
        }
    }



}
