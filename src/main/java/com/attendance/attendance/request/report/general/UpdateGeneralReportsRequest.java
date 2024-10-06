package com.attendance.attendance.request.report.general;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
@Schema(name = "Update General Reports Request", description = "Request to update an existing general report, including report ID, name, and content")
public class UpdateGeneralReportsRequest {

    @Schema(description = "ID of the report", example = "1")
    @NotNull(message = "Report ID is mandatory")
    private Long id;

    @Schema(description = "Name of the report", example = "Monthly Attendance Report")
    @NotBlank(message = "Report name is mandatory")
    private String reportName;

    @Schema(description = "Content of the report", example = "This report contains the updated attendance details for the month of October.")
    @NotBlank(message = "Report content is mandatory")
    private String reportContent;
}