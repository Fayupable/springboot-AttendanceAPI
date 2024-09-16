package com.attendance.attendance.controller;


import com.attendance.attendance.entity.Person;
import com.attendance.attendance.entity.University;
import com.attendance.attendance.request.AddPersonRequest;
import com.attendance.attendance.request.AddUniversityRequest;
import com.attendance.attendance.response.ApiResponse;
import com.attendance.attendance.service.university.IUniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/university")
public class UniversityController {

    private final IUniversityService universityService;

    @PostMapping("/add")
    @Transactional
    public ResponseEntity<ApiResponse> createUniversity(@RequestBody AddUniversityRequest request) {
        try {
            University createdUniversity = universityService.addUniversity(request);
            return ResponseEntity.ok(new ApiResponse("University created successfully", createdUniversity));
        } catch (Exception e) {
            return ResponseEntity.status(409).body(new ApiResponse(e.getMessage(), null));
        }
    }


}
