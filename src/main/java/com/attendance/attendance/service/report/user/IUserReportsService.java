package com.attendance.attendance.service.report.user;

import com.attendance.attendance.dto.UserReportsDto;
import com.attendance.attendance.entity.UserReports;
import com.attendance.attendance.request.report.user.AddUserReportsRequest;
import com.attendance.attendance.request.report.user.UpdateUserReportsRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IUserReportsService {
    List<UserReports> getAllUserReports();

    UserReports getUserReportById(Long id);

    UserReports addUserReport(AddUserReportsRequest request);

    UserReports updateUserReport(UpdateUserReportsRequest request, Long id);

    void deleteUserReport(Long id);

    List<UserReports> getUserReportsByTitle(String reportName);

    List<UserReports> getUserReportsByDate(LocalDate date);

    List<UserReports> getUserReportsByTitleAndDate(String reportName, LocalDate date);

    List<UserReports> getUserReportsByTitleAndContentContaining(String reportName, String content);

    List<UserReports> getUserReportsByDateBetween(LocalDate startDate, LocalDate endDate);

    List<UserReports> getUserReportsByTitleAndDateBetween(String reportName, LocalDate startDate, LocalDate endDate);

    List<UserReports> getUserReportsByTitleAndContentContainingAndDateBetween(String reportName, String content, LocalDate startDate, LocalDate endDate);

    List<UserReports> getUserReportsByContentContaining(String content);

    List<UserReports> getUserReportsByPersonId(Long personId);

    UserReportsDto convertToDto(UserReports userReports);

    List<UserReportsDto> convertToDto(List<UserReports> userReports);
}
