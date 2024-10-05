package com.attendance.attendance.controller;

import com.attendance.attendance.dto.UniversityCourseDto;
import com.attendance.attendance.entity.UniversityCourse;
import com.attendance.attendance.request.university.course.course.AddUniversityCourseRequest;
import com.attendance.attendance.request.university.course.course.UpdateUniversityCourseRequest;
import com.attendance.attendance.response.ApiResponse;
import com.attendance.attendance.service.university.IUniversityCourseService;
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

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllUniversityCourses() {
        List<UniversityCourse> universityCourses = universityCourseService.getAllCourses();
        List<UniversityCourseDto> universityCourseDtos = universityCourseService.convertToDtoToList(universityCourses);
        return ResponseEntity.ok(new ApiResponse("University courses retrieved successfully", universityCourseDtos));
    }

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

    @GetMapping("/name/{courseName}")
    public ResponseEntity<ApiResponse> getUniversityCourseByName(@PathVariable String courseName) {
        List<UniversityCourse> universityCourses = universityCourseService.getCourseByName(courseName);
        List<UniversityCourseDto> universityCourseDtos = universityCourseService.convertToDtoToList(universityCourses);
        return ResponseEntity.ok(new ApiResponse("University courses retrieved successfully", universityCourseDtos));
    }

    @GetMapping("/code/{courseCode}")
    public ResponseEntity<ApiResponse> getUniversityCourseByCode(@PathVariable String courseCode) {
        List<UniversityCourse> universityCourses = universityCourseService.getCourseByCode(courseCode);
        List<UniversityCourseDto> universityCourseDto = universityCourseService.convertToDtoToList(universityCourses);
        return ResponseEntity.ok(new ApiResponse("University courses retrieved successfully", universityCourseDto));
    }

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
