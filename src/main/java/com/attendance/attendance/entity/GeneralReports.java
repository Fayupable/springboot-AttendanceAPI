package com.attendance.attendance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "general_reports")
public class GeneralReports {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "report_name", length = 100, nullable = false)
    private String reportName;

    @Column(name = "report_date", nullable = false)
    private LocalDate reportDate;

    @Lob
    @Column(name = "report_content")
    private String reportContent;

    public GeneralReports(String reportName, LocalDate reportDate, String reportContent) {
        this.reportName = reportName;
        this.reportDate = reportDate;
        this.reportContent = reportContent;
    }
}