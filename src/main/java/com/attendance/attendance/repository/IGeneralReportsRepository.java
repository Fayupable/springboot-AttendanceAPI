package com.attendance.attendance.repository;

import com.attendance.attendance.entity.GeneralReports;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface IGeneralReportsRepository extends JpaRepository<GeneralReports, Long> {

    List<GeneralReports> findByReportDate(LocalDate reportDate);

    List<GeneralReports> findByReportDateBetween(LocalDate startDate, LocalDate endDate);

    List<GeneralReports> findGeneralReportsByReportContentContaining(String content);

    List<GeneralReports> findGeneralReportsByReportName(String reportName);

    List<GeneralReports> findGeneralReportsByReportNameAndReportDate(String reportName, LocalDate reportDate);

    List<GeneralReports> findGeneralReportsByReportNameAndReportDateBetween(String reportName, LocalDate startDate, LocalDate endDate);

    List<GeneralReports> findGeneralReportsByReportNameAndReportContentContaining(String reportName, String content);


}
