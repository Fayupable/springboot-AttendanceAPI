package com.attendance.attendance.repository;

import com.attendance.attendance.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILogRepository extends JpaRepository<Log,Long> {

}
