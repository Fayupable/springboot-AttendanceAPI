package com.attendance.attendance.controller;

import com.attendance.attendance.dto.UniversityDto;
import com.attendance.attendance.entity.University;
import com.attendance.attendance.request.university.university.AddUniversityRequest;
import com.attendance.attendance.request.university.university.UpdateUniversityRequest;
import com.attendance.attendance.response.ApiResponse;
import com.attendance.attendance.service.university.IUniversityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/university")
@Tag(name = "University")
public class UniversityController {

    private final IUniversityService universityService;

    @Operation(
            description = "Get university by id",
            summary = "Get university by id",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            description = "University retrieved successfully",
                            responseCode = "200"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            description = "University not found",
                            responseCode = "404"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            description = "Unauthorized/Invalid token",
                            responseCode = "403"
                    ),
            }
    )
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{universityId}")
    public ResponseEntity<ApiResponse> getUniversityById(@PathVariable Long universityId) {
        try {
            University university = universityService.getUniversityById(universityId);
            UniversityDto universityDto = universityService.convertToDto(university);
            return ResponseEntity.ok(new ApiResponse("University retrieved successfully", universityDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @Operation(
            description = "Get all universities",
            summary = "Get all universities",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            description = "Universities retrieved successfully",
                            responseCode = "200"
                    )
            }
    )
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllUniversities() {
        List<University> universities = universityService.getAllUniversity();
        List<UniversityDto> universityDto = universityService.getConvertedUniversity(universities);
        return ResponseEntity.ok(new ApiResponse("University retrieved successfully", universityDto));
    }

    @Operation(
            description = "Get university by name",
            summary = "Get university by name",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            description = "University retrieved successfully",
                            responseCode = "200"
                    )
            }
    )
    @GetMapping("/name/{universityName}")
    public ResponseEntity<ApiResponse> getUniversityByName(@PathVariable String universityName) {
        List<University> universities = universityService.getUniversityByName(universityName);
        List<UniversityDto> universityDto = universityService.getConvertedUniversity(universities);
        return ResponseEntity.ok(new ApiResponse("University retrieved successfully", universityDto));
    }

    @Operation(
            description = "Create a new university",
            summary = "Create a new university",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            description = "University created successfully",
                            responseCode = "200"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            description = "Conflict occurred",
                            responseCode = "409"
                    )
            }
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    @Transactional
    public ResponseEntity<ApiResponse> createUniversity(@RequestBody AddUniversityRequest request, Authentication authentication) {
        try {
            University createdUniversity = universityService.addUniversity(request);
            UniversityDto universityDto = universityService.convertToDto(createdUniversity);
            return ResponseEntity.ok(new ApiResponse("University created successfully", universityDto));
        } catch (Exception e) {
            return ResponseEntity.status(409).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @Operation(
            description = "Delete a university by id",
            summary = "Delete a university by id",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            description = "University deleted successfully",
                            responseCode = "200"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            description = "University not found",
                            responseCode = "404"
                    )
            }
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{universityId}")
    @Transactional
    public ResponseEntity<ApiResponse> deleteUniversity(@PathVariable Long universityId) {
        try {
            universityService.deleteUniversity(universityId);
            return ResponseEntity.ok(new ApiResponse("University deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @Operation(
            description = "Update a university by id",
            summary = "Update a university by id",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            description = "University updated successfully",
                            responseCode = "200"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            description = "University not found",
                            responseCode = "404"
                    )
            }
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{universityId}")
    @Transactional
    public ResponseEntity<ApiResponse> updateUniversity(@RequestBody UpdateUniversityRequest request, @PathVariable Long universityId) {
        try {
            University updatedUniversity = universityService.updateUniversity(request, universityId);
            UniversityDto universityDto = universityService.convertToDto(updatedUniversity);
            return ResponseEntity.ok(new ApiResponse("University updated successfully", universityDto));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
        }
    }
}