package com.attendance.attendance.repository;

import com.attendance.attendance.entity.UserReports;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserReportsRepository extends JpaRepository<UserReports, Long> {

}
