package com.attendance.attendance.request.report.general;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AddGeneralReportsRequest {
    @NotBlank(message = "Report name is mandatory")
    private String reportName;

    @NotNull(message = "Report date is mandatory")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate reportDate;

    @NotBlank(message = "Report content is mandatory")
    private String reportContent;
}
