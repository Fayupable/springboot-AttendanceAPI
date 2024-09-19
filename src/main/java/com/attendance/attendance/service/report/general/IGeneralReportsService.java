package com.attendance.attendance.service.report.general;

import com.attendance.attendance.dto.GeneralReportsDto;
import com.attendance.attendance.entity.GeneralReports;
import com.attendance.attendance.request.report.general.AddGeneralReportsRequest;
import com.attendance.attendance.request.report.general.UpdateGeneralReportsRequest;

import java.time.LocalDate;
import java.util.List;

public interface IGeneralReportsService {


    List<GeneralReports> getAllGeneralReports();

    GeneralReports getGeneralReportById(Long id);

    List<GeneralReports> getGeneralReportBetweenDates(LocalDate startDate, LocalDate endDate);

    List<GeneralReports> getGeneralReportByDate(LocalDate date);

    GeneralReports addGeneralReport(AddGeneralReportsRequest request);

    GeneralReports updateGeneralReport(UpdateGeneralReportsRequest request, Long id);

    void deleteGeneralReport(Long id);

    GeneralReportsDto convertToDto(GeneralReports generalReports);

    List<GeneralReportsDto> convertToDto(List<GeneralReports> generalReports);


}
