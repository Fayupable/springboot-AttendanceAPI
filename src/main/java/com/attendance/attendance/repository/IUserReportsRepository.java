package com.attendance.attendance.repository;

import com.attendance.attendance.entity.UserReports;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IUserReportsRepository extends JpaRepository<UserReports, Long> {
    List<UserReports> findUserReportsByTitle(String reportName);

    List<UserReports> findUserReportsByDate(LocalDate date);

    List<UserReports> findUserReportsByTitleAndDate(String reportName, LocalDate date);

    List<UserReports> findUserReportsByTitleAndContentContaining(String reportName, String content);

    List<UserReports> findUserReportsByDateBetween(LocalDate startDate, LocalDate endDate);

    List<UserReports> findUserReportsByTitleAndDateBetween(String reportName, LocalDate startDate, LocalDate endDate);

    List<UserReports> findUserReportsByTitleAndContentContainingAndDateBetween(String reportName, String content, LocalDate startDate, LocalDate endDate);

    List<UserReports> findUserReportsByContentContaining(String content);

    List<UserReports> findUserReportsByPersonId(Long personId);


}
