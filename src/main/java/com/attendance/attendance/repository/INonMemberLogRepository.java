package com.attendance.attendance.repository;

import com.attendance.attendance.entity.NonMemberLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INonMemberLogRepository extends JpaRepository<NonMemberLog, Long> {
}
