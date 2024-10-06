package com.attendance.attendance.request.report.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(name = "Update User Reports Request", description = "Request to update an existing user report, including report ID, date, person ID, title, and content")
public class UpdateUserReportsRequest {

    @Schema(description = "ID of the report", example = "1")
    @NotNull(message = "Report ID is mandatory")
    private Long id;

    @Schema(description = "Title of the report", example = "Weekly Summary")
    @NotNull(message = "Title is mandatory")
    private String title;

    @Schema(description = "Content of the report", example = "This report contains the updated weekly summary of activities.")
    @NotNull(message = "Content is mandatory")
    private String content;

    @Schema(description = "Date of the report", example = "2023-10-01T12:00:00")
    @NotNull(message = "Date is mandatory")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime date;

    @Schema(description = "ID of the person", example = "123")
    @NotNull(message = "Person ID is mandatory")
    private Long personId;
}