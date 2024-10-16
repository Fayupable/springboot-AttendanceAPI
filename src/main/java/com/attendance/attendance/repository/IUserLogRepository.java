package com.attendance.attendance.repository;

import com.attendance.attendance.entity.UserLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserLogRepository extends JpaRepository<UserLog,Long> {

}
