package com.attendance.attendance.controller;

import com.attendance.attendance.dto.UniversityDepartmentDto;
import com.attendance.attendance.entity.UniversityDepartment;
import com.attendance.attendance.exceptions.AlreadyExistsException;
import com.attendance.attendance.request.university.AddUniversityDepartmentRequest;
import com.attendance.attendance.response.ApiResponse;
import com.attendance.attendance.service.university.IUniversityDepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.hibernate.sql.ast.Clause.CONFLICT;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/university-department")
public class UniversityDepartmentController {

    private final IUniversityDepartmentService universityDepartmentService;

    @PostMapping("/add")
    @Transactional
    public ResponseEntity<ApiResponse> addDepartment(@RequestBody AddUniversityDepartmentRequest department) {
        try {
            UniversityDepartment theDepartment = universityDepartmentService.addDepartment(department);
            UniversityDepartmentDto departmentDto = universityDepartmentService.convertToDto(theDepartment);
            return ResponseEntity.ok(new ApiResponse("Add department success!", departmentDto));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(409).body(new ApiResponse(e.getMessage(), null));
        }
    }


}
