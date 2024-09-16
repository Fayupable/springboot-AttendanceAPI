package com.attendance.attendance.controller;


import com.attendance.attendance.dto.UniversityDto;
import com.attendance.attendance.entity.University;
import com.attendance.attendance.request.university.AddUniversityRequest;
import com.attendance.attendance.response.ApiResponse;
import com.attendance.attendance.service.university.IUniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/university")
public class UniversityController {

    private final IUniversityService universityService;

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

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllUniversities() {
        List<University> universities = universityService.getAllUniversity();
        List<UniversityDto> universityDto = universityService.getConvertedUniversity(universities);
        return ResponseEntity.ok(new ApiResponse("University retrieved successfully", universityDto));
    }

    @PostMapping("/add")
    @Transactional
    public ResponseEntity<ApiResponse> createUniversity(@RequestBody AddUniversityRequest request) {
        try {
            University createdUniversity = universityService.addUniversity(request);
            UniversityDto universityDto = universityService.convertToDto(createdUniversity);
            return ResponseEntity.ok(new ApiResponse("University created successfully", universityDto));
        } catch (Exception e) {
            return ResponseEntity.status(409).body(new ApiResponse(e.getMessage(), null));
        }
    }

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


}
