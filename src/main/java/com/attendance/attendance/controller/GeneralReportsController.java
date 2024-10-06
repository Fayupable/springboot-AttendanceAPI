package com.attendance.attendance.controller;

import com.attendance.attendance.dto.GeneralReportsDto;
import com.attendance.attendance.entity.GeneralReports;
import com.attendance.attendance.request.report.general.AddGeneralReportsRequest;
import com.attendance.attendance.request.report.general.UpdateGeneralReportsRequest;
import com.attendance.attendance.response.ApiResponse;
import com.attendance.attendance.service.report.general.IGeneralReportsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/general-reports")
@Tag(name = "General Reports")
public class GeneralReportsController {
    private final IGeneralReportsService generalReportsService;

    @Operation(
            summary = "Get all general reports",
            description = "Retrieve all general reports",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "General Reports retrieved successfully")
            }
    )
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllGeneralReports() {
        List<GeneralReports> generalReports = generalReportsService.getAllGeneralReports();
        List<GeneralReportsDto> generalReportsDto = generalReportsService.convertToDto(generalReports);
        return ResponseEntity.ok(new ApiResponse("General Reports retrieved successfully", generalReportsDto));
    }

    @Operation(
            summary = "Get general report by ID",
            description = "Retrieve general report by its ID",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "General Report retrieved successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "General Report not found")
            }
    )
    @GetMapping("/generalReportId/{generalReportId}")
    public ResponseEntity<ApiResponse> getGeneralReportById(@PathVariable Long generalReportId) {
        try {
            GeneralReports generalReports = generalReportsService.getGeneralReportById(generalReportId);
            GeneralReportsDto generalReportsDto = generalReportsService.convertToDto(generalReports);
            return ResponseEntity.ok(new ApiResponse("General Report retrieved successfully", generalReportsDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get general report by date",
            description = "Retrieve general report by date",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "General Report retrieved successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "General Report not found")
            }
    )
    @GetMapping("/generalReportDate/{generalReportDate}")
    public ResponseEntity<ApiResponse> getGeneralReportByDate(@PathVariable LocalDate generalReportDate) {
        try {
            List<GeneralReports> generalReports = generalReportsService.getGeneralReportByDate(generalReportDate);
            List<GeneralReportsDto> generalReportsDto = generalReportsService.convertToDto(generalReports);
            return ResponseEntity.ok(new ApiResponse("General Report retrieved successfully", generalReportsDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get general report between dates",
            description = "Retrieve general report between start and end dates",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "General Report retrieved successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "General Report not found")
            }
    )
    @GetMapping("/generalReportStartDate/{generalReportStartDate}/generalReportEndDate/{generalReportEndDate}")
    public ResponseEntity<ApiResponse> getGeneralReportBetweenDates(@PathVariable LocalDate generalReportStartDate, @PathVariable LocalDate generalReportEndDate) {
        try {
            List<GeneralReports> generalReports = generalReportsService.getGeneralReportBetweenDates(generalReportStartDate, generalReportEndDate);
            List<GeneralReportsDto> generalReportsDto = generalReportsService.convertToDto(generalReports);
            return ResponseEntity.ok(new ApiResponse("General Report retrieved successfully", generalReportsDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @Operation(
            summary = "Add general report",
            description = "Add a new general report",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "General Report added successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request")
            }
    )
    @PostMapping("/add")
    @Transactional
    public ResponseEntity<ApiResponse> addGeneralReport(@RequestBody AddGeneralReportsRequest request) {
        try {
            GeneralReports newGeneralReport = generalReportsService.addGeneralReport(request);
            GeneralReportsDto generalReportsDto = generalReportsService.convertToDto(newGeneralReport);
            return ResponseEntity.ok(new ApiResponse("General Report added successfully", generalReportsDto));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @Operation(
            summary = "Update general report",
            description = "Update an existing general report",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "General Report updated successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request")
            }
    )
    @PutMapping("/update/{generalReportId}")
    @Transactional
    public ResponseEntity<ApiResponse> updateGeneralReport(@RequestBody UpdateGeneralReportsRequest request, @PathVariable Long generalReportId) {
        try {
            GeneralReports updatedGeneralReport = generalReportsService.updateGeneralReport(request, generalReportId);
            GeneralReportsDto generalReportsDto = generalReportsService.convertToDto(updatedGeneralReport);
            return ResponseEntity.ok(new ApiResponse("General Report updated successfully", generalReportsDto));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @Operation(
            summary = "Delete general report",
            description = "Delete a general report by its ID",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "General Report deleted successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request")
            }
    )
    @DeleteMapping("/delete/{generalReportId}")
    @Transactional
    public ResponseEntity<ApiResponse> deleteGeneralReport(@PathVariable Long generalReportId) {
        try {
            generalReportsService.deleteGeneralReport(generalReportId);
            return ResponseEntity.ok(new ApiResponse("General Report deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new ApiResponse(e.getMessage(), null));
        }
    }
}