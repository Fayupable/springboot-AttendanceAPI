package com.attendance.attendance.controller;

import com.attendance.attendance.dto.UserReportsDto;
import com.attendance.attendance.entity.UserReports;
import com.attendance.attendance.request.report.user.AddUserReportsRequest;
import com.attendance.attendance.request.report.user.UpdateUserReportsRequest;
import com.attendance.attendance.response.ApiResponse;
import com.attendance.attendance.service.report.user.IUserReportsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/user-reports")
@Tag(name = "User Reports")
public class UserReportsController {
    private final IUserReportsService userReportsService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllUserReports() {
        List<UserReports> userReports = userReportsService.getAllUserReports();
        List<UserReportsDto> userReportsDto = userReportsService.convertToDto(userReports);
        return ResponseEntity.ok(new ApiResponse("User Reports retrieved successfully", userReportsDto));
    }

    @GetMapping("/userReportId/{userReportId}")
    public ResponseEntity<ApiResponse> getUserReportById(@PathVariable Long userReportId) {
        try {
            UserReports userReports = userReportsService.getUserReportById(userReportId);
            UserReportsDto userReportsDto = userReportsService.convertToDto(userReports);
            return ResponseEntity.ok(new ApiResponse("User Report retrieved successfully", userReportsDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/userReportPersonId/{userReportPersonId}")
    public ResponseEntity<ApiResponse> getUserReportByPersonId(@PathVariable Long userReportPersonId) {
        try {
            List<UserReports> userReports = userReportsService.getUserReportsByPersonId(userReportPersonId);
            List<UserReportsDto> userReportsDto = userReportsService.convertToDto(userReports);
            return ResponseEntity.ok(new ApiResponse("User Report retrieved successfully", userReportsDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/userReportDate/{userReportDate}")
    public ResponseEntity<ApiResponse> getUserReportByDate(@PathVariable LocalDate userReportDate) {
        try {
            List<UserReports> userReports = userReportsService.getUserReportsByDate(userReportDate);
            List<UserReportsDto> userReportsDto = userReportsService.convertToDto(userReports);
            return ResponseEntity.ok(new ApiResponse("User Report retrieved successfully", userReportsDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/userReportStartDate/{userReportStartDate}/userReportEndDate/{userReportEndDate}")
    public ResponseEntity<ApiResponse> getUserReportBetweenDates(@PathVariable LocalDate userReportStartDate, @PathVariable LocalDate userReportEndDate) {
        try {
            List<UserReports> userReports = userReportsService.getUserReportsByDateBetween(userReportStartDate, userReportEndDate);
            List<UserReportsDto> userReportsDto = userReportsService.convertToDto(userReports);
            return ResponseEntity.ok(new ApiResponse("User Report retrieved successfully", userReportsDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping("/add")
    @Transactional
    public ResponseEntity<ApiResponse> addUserReport(@RequestBody AddUserReportsRequest request) {
        try {
            UserReports userReports = userReportsService.addUserReport(request);
            UserReportsDto userReportsDto = userReportsService.convertToDto(userReports);
            return ResponseEntity.ok(new ApiResponse("User Report added successfully", userReportsDto));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/update/{reportId}")
    @Transactional
    public ResponseEntity<ApiResponse> updateUserReport(@RequestBody UpdateUserReportsRequest request, @PathVariable Long reportId) {
        try {
            UserReports updatedUserReports = userReportsService.updateUserReport(request, reportId);
            UserReportsDto userReportsDto = userReportsService.convertToDto(updatedUserReports);
            return ResponseEntity.ok(new ApiResponse("User Report updated successfully", userReportsDto));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/delete/{userReportId}")
    @Transactional
    public ResponseEntity<ApiResponse> deleteUserReport(@PathVariable Long userReportId) {
        try {
            userReportsService.deleteUserReport(userReportId);
            return ResponseEntity.ok(new ApiResponse("User Report deleted successfully", null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
        }
    }


}
