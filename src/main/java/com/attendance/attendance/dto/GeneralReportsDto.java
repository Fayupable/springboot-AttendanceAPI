package com.attendance.attendance.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class GeneralReportsDto {
    private Long id;
    private String reportName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate reportDate;

    private String reportContent;

}
