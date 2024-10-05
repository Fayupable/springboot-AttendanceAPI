package com.attendance.attendance.controller;

import com.attendance.attendance.dto.GeneralReportsDto;
import com.attendance.attendance.entity.GeneralReports;
import com.attendance.attendance.request.report.general.AddGeneralReportsRequest;
import com.attendance.attendance.request.report.general.UpdateGeneralReportsRequest;
import com.attendance.attendance.response.ApiResponse;
import com.attendance.attendance.service.report.general.IGeneralReportsService;
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


    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllGeneralReports() {
        List<GeneralReports> generalReports = generalReportsService.getAllGeneralReports();
        List<GeneralReportsDto> generalReportsDto = generalReportsService.convertToDto(generalReports);
        return ResponseEntity.ok(new ApiResponse("General Reports retrieved successfully", generalReportsDto));
    }

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

    @PostMapping("/add")
    @Transactional
    public ResponseEntity<ApiResponse> addGeneralReport(@RequestBody AddGeneralReportsRequest request) {
        try {
            GeneralReports generalReports = generalReportsService.addGeneralReport(request);
            GeneralReportsDto generalReportsDto = generalReportsService.convertToDto(generalReports);
            return ResponseEntity.ok(new ApiResponse("General Report added successfully", generalReportsDto));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    @PutMapping("/update/{generalReportId}")
    @Transactional
    public ResponseEntity<ApiResponse> updateGeneralReport(@RequestBody UpdateGeneralReportsRequest request, @PathVariable Long generalReportId) {
        try {
            GeneralReports updatedGeneralReports = generalReportsService.updateGeneralReport(request, generalReportId);
            GeneralReportsDto generalReportsDto = generalReportsService.convertToDto(updatedGeneralReports);
            return ResponseEntity.ok(new ApiResponse("General Report updated successfully", generalReportsDto));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/delete/{generalReportId}")
    @Transactional
    public ResponseEntity<ApiResponse> deleteGeneralReport(@PathVariable Long generalReportId) {
        try {
            generalReportsService.deleteGeneralReport(generalReportId);
            return ResponseEntity.ok(new ApiResponse("General Report deleted successfully", null));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
