package com.attendance.attendance.request.report.general;

import lombok.Data;

@Data
public class UpdateGeneralReportsRequest {
    private Long id;
    private String reportName;
    private String reportContent;
}
