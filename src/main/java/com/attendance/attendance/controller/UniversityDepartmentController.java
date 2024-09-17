package com.attendance.attendance.controller;

import com.attendance.attendance.dto.UniversityDepartmentDto;
import com.attendance.attendance.entity.UniversityDepartment;
import com.attendance.attendance.exceptions.AlreadyExistsException;
import com.attendance.attendance.request.university.AddUniversityDepartmentRequest;
import com.attendance.attendance.request.university.UpdateUniversityDepartmentRequest;
import com.attendance.attendance.response.ApiResponse;
import com.attendance.attendance.service.university.IUniversityDepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.hibernate.sql.ast.Clause.CONFLICT;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/university-department")
public class UniversityDepartmentController {

    private final IUniversityDepartmentService universityDepartmentService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllDepartments() {
        List<UniversityDepartment> departments = universityDepartmentService.getAllDepartment();
        List<UniversityDepartmentDto> convertedDepartments = universityDepartmentService.convertToDtoList(departments);
        return ResponseEntity.ok(new ApiResponse("success", convertedDepartments));
    }

    @GetMapping("/{departmentId}")
    public ResponseEntity<ApiResponse> getDepartmentById(@PathVariable Long departmentId) {
        try {
            UniversityDepartment department = universityDepartmentService.getDepartmentById(departmentId);
            UniversityDepartmentDto departmentDto = universityDepartmentService.convertToDto(department);
            return ResponseEntity.ok(new ApiResponse("Department retrieved successfully", departmentDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/department/{departmentName}")
    public ResponseEntity<ApiResponse> getDepartmentByName(@PathVariable String departmentName) {
        List<UniversityDepartment> departments = universityDepartmentService.getDepartmentByName(departmentName);
        List<UniversityDepartmentDto> departmentDto = universityDepartmentService.convertToDtoList(departments);
        return ResponseEntity.ok(new ApiResponse("Department retrieved successfully", departmentDto));
    }


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

    @PutMapping("/department/{departmentId}/update")
    @Transactional
    public ResponseEntity<ApiResponse> updateDepartment(@RequestBody UpdateUniversityDepartmentRequest request, @PathVariable Long departmentId) {
        try {
            UniversityDepartment theDepartment = universityDepartmentService.updateDepartment(request, departmentId);
            UniversityDepartmentDto departmentDto = universityDepartmentService.convertToDto(theDepartment);
            return ResponseEntity.ok(new ApiResponse("Department updated successfully", departmentDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/department/{departmentId}/delete")
    @Transactional
    public ResponseEntity<ApiResponse> deleteDepartment(@PathVariable Long departmentId) {
        try {
            universityDepartmentService.deleteDepartment(departmentId);
            return ResponseEntity.ok(new ApiResponse("Department deleted successfully", null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
        }
    }


}
