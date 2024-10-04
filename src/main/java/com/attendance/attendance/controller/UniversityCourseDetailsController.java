package com.attendance.attendance.controller;

import com.attendance.attendance.dto.UniversityCourseDetailsDto;
import com.attendance.attendance.entity.UniversityCourseDetails;
import com.attendance.attendance.request.university.course.detail.AddUniversityCourseDetailsRequest;
import com.attendance.attendance.request.university.course.detail.UpdateUniversityCourseDetailsRequest;
import com.attendance.attendance.response.ApiResponse;
import com.attendance.attendance.service.university.IUniversityCourseDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/university-course-details")
public class UniversityCourseDetailsController {
    private final IUniversityCourseDetailsService universityCourseDetailsService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllUniversityCourseDetails() {
        List<UniversityCourseDetails> universityCourseDetails = universityCourseDetailsService.getAllCourseDetails();
        List<UniversityCourseDetailsDto> universityCourseDetailsDto = universityCourseDetailsService.convertToDtoToList(universityCourseDetails);
        return ResponseEntity.ok(new ApiResponse("University course details retrieved successfully", universityCourseDetailsDto));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<ApiResponse> getUniversityCourseDetailsByCourseId(@PathVariable Long courseId) {
        List<UniversityCourseDetails> universityCourseDetails = universityCourseDetailsService.getCourseDetailsByCourseId(courseId);
        List<UniversityCourseDetailsDto> universityCourseDetailsDto = universityCourseDetailsService.convertToDtoToList(universityCourseDetails);
        return ResponseEntity.ok(new ApiResponse("University course details retrieved successfully", universityCourseDetailsDto));
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<ApiResponse> getUniversityCourseDetailsById(@PathVariable Long id) {
        try {
            UniversityCourseDetails universityCourseDetails = universityCourseDetailsService.getCourseDetailsById(id);
            UniversityCourseDetailsDto universityCourseDetailsDto = universityCourseDetailsService.convertToDto(universityCourseDetails);
            return ResponseEntity.ok(new ApiResponse("University course details retrieved successfully", universityCourseDetailsDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
        }
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/courses/details/add")
    public ResponseEntity<ApiResponse> addUniversityCourseDetails(@RequestBody AddUniversityCourseDetailsRequest request) {
        try {
            UniversityCourseDetails universityCourseDetails = universityCourseDetailsService.addCourseDetails(request);
            UniversityCourseDetailsDto universityCourseDetailsDto = universityCourseDetailsService.convertToDto(universityCourseDetails);
            return ResponseEntity.ok(new ApiResponse("University course details added successfully", universityCourseDetailsDto));
        } catch (Exception e) {
            return ResponseEntity.status(409).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/courses/{courseId}/details/update/{id}")
    public ResponseEntity<ApiResponse> updateUniversityCourseDetails(@PathVariable Long courseId, @PathVariable Long id, @RequestBody UpdateUniversityCourseDetailsRequest request) {
        try {
            UniversityCourseDetails universityCourseDetails = universityCourseDetailsService.updateCourseDetails(request, id);
            UniversityCourseDetailsDto universityCourseDetailsDto = universityCourseDetailsService.convertToDto(universityCourseDetails);
            return ResponseEntity.ok(new ApiResponse("University course details updated successfully", universityCourseDetailsDto));
        } catch (Exception e) {
            return ResponseEntity.status(409).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteUniversityCourseDetails(@PathVariable Long id) {
        try {
            universityCourseDetailsService.deleteCourseDetails(id);
            return ResponseEntity.ok(new ApiResponse("University course details deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(409).body(new ApiResponse(e.getMessage(), null));
        }
    }
}



