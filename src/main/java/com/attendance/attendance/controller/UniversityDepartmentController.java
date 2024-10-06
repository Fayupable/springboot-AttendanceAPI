package com.attendance.attendance.controller;

import com.attendance.attendance.dto.UniversityDepartmentDto;
import com.attendance.attendance.entity.UniversityDepartment;
import com.attendance.attendance.exceptions.AlreadyExistsException;
import com.attendance.attendance.request.university.department.AddUniversityDepartmentRequest;
import com.attendance.attendance.request.university.department.UpdateUniversityDepartmentRequest;
import com.attendance.attendance.response.ApiResponse;
import com.attendance.attendance.service.university.IUniversityDepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/university-department")
@Tag(name = "University Department")
public class UniversityDepartmentController {

    private final IUniversityDepartmentService universityDepartmentService;

    @Operation(
            summary = "Get all departments",
            description = "Retrieve all university departments",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Departments retrieved successfully")
            }
    )
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllDepartments() {
        List<UniversityDepartment> departments = universityDepartmentService.getAllDepartment();
        List<UniversityDepartmentDto> convertedDepartments = universityDepartmentService.convertToDtoList(departments);
        return ResponseEntity.ok(new ApiResponse("Departments retrieved successfully", convertedDepartments));
    }

    @Operation(
            summary = "Get department by ID",
            description = "Retrieve a university department by its ID",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Department retrieved successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Department not found")
            }
    )
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

    @Operation(
            summary = "Get department by name",
            description = "Retrieve a university department by its name",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Department retrieved successfully")
            }
    )
    @GetMapping("/department/{departmentName}")
    public ResponseEntity<ApiResponse> getDepartmentByName(@PathVariable String departmentName) {
        List<UniversityDepartment> departments = universityDepartmentService.getDepartmentByName(departmentName);
        List<UniversityDepartmentDto> departmentDto = universityDepartmentService.convertToDtoList(departments);
        return ResponseEntity.ok(new ApiResponse("Department retrieved successfully", departmentDto));
    }

    @Operation(
            summary = "Add department",
            description = "Add a new university department",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Department added successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "Conflict")
            }
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    @Transactional
    public ResponseEntity<ApiResponse> addDepartment(@RequestBody AddUniversityDepartmentRequest department) {
        try {
            UniversityDepartment theDepartment = universityDepartmentService.addDepartment(department);
            UniversityDepartmentDto departmentDto = universityDepartmentService.convertToDto(theDepartment);
            return ResponseEntity.ok(new ApiResponse("Department added successfully", departmentDto));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(409).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @Operation(
            summary = "Update department",
            description = "Update an existing university department",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Department updated successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Department not found")
            }
    )
    @PreAuthorize("hasRole('ADMIN')")
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

    @Operation(
            summary = "Delete department",
            description = "Delete a university department by its ID",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Department deleted successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Department not found")
            }
    )
    @PreAuthorize("hasRole('ADMIN')")
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