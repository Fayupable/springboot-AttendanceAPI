package com.attendance.attendance.controller;

import com.attendance.attendance.dto.CoursesRequirementsDto;
import com.attendance.attendance.dto.UniversityCourseDto;
import com.attendance.attendance.entity.CoursesRequirements;
import com.attendance.attendance.entity.UniversityCourse;
import com.attendance.attendance.request.university.course.requirement.AddCourseRequirementsRequest;
import com.attendance.attendance.response.ApiResponse;
import com.attendance.attendance.service.university.IUniversityCourseRequirementsService;
import com.attendance.attendance.service.university.IUniversityCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/add")
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


}
