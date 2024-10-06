package com.attendance.attendance.controller;

import com.attendance.attendance.dto.UniversityCourseDetailsDto;
import com.attendance.attendance.entity.UniversityCourseDetails;
import com.attendance.attendance.request.university.course.detail.AddUniversityCourseDetailsRequest;
import com.attendance.attendance.request.university.course.detail.UpdateUniversityCourseDetailsRequest;
import com.attendance.attendance.response.ApiResponse;
import com.attendance.attendance.service.university.IUniversityCourseDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/university-course-details")
@Tag(name = "University Course Details")
public class UniversityCourseDetailsController {
    private final IUniversityCourseDetailsService universityCourseDetailsService;

    @Operation(
            summary = "Get all university course details",
            description = "Retrieve all university course details",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "University course details retrieved successfully")
            }
    )
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllUniversityCourseDetails() {
        List<UniversityCourseDetails> universityCourseDetails = universityCourseDetailsService.getAllCourseDetails();
        List<UniversityCourseDetailsDto> universityCourseDetailsDto = universityCourseDetailsService.convertToDtoToList(universityCourseDetails);
        return ResponseEntity.ok(new ApiResponse("University course details retrieved successfully", universityCourseDetailsDto));
    }

    @Operation(
            summary = "Get university course details by course ID",
            description = "Retrieve university course details by course ID",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "University course details retrieved successfully")
            }
    )
    @GetMapping("/course/{courseId}")
    public ResponseEntity<ApiResponse> getUniversityCourseDetailsByCourseId(@PathVariable Long courseId) {
        List<UniversityCourseDetails> universityCourseDetails = universityCourseDetailsService.getCourseDetailsByCourseId(courseId);
        List<UniversityCourseDetailsDto> universityCourseDetailsDto = universityCourseDetailsService.convertToDtoToList(universityCourseDetails);
        return ResponseEntity.ok(new ApiResponse("University course details retrieved successfully", universityCourseDetailsDto));
    }

    @Operation(
            summary = "Get university course details by ID",
            description = "Retrieve university course details by ID",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "University course details retrieved successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "University course details not found")
            }
    )
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

    @Operation(
            summary = "Add university course details",
            description = "Add new university course details",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "University course details added successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "Conflict")
            }
    )
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

    @Operation(
            summary = "Update university course details",
            description = "Update existing university course details",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "University course details updated successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "Conflict")
            }
    )
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

    @Operation(
            summary = "Delete university course details",
            description = "Delete university course details by ID",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "University course details deleted successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "Conflict")
            }
    )
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