package com.attendance.attendance.request.report.general;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(name = "Add General Reports Request", description = "Request to add a new general report, including report name, date, and content")
public class AddGeneralReportsRequest {

    @Schema(description = "Name of the report", example = "Monthly Attendance Report")
    @NotBlank(message = "Report name is mandatory")
    private String reportName;

    @Schema(description = "Date of the report", example = "2023-10-01")
    @NotNull(message = "Report date is mandatory")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate reportDate;

    @Schema(description = "Content of the report", example = "This report contains the attendance details for the month of October.")
    @NotBlank(message = "Report content is mandatory")
    private String reportContent;
}