package com.attendance.attendance.repository;


import com.attendance.attendance.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface IScheduleRepository extends JpaRepository<Schedule, Long> {
    boolean existsByCourse_CourseId(Long courseId);
    boolean existsByCourse_CourseIdAndStartTimeBetween(Long courseId, LocalDateTime startTime, LocalDateTime endTime);
    boolean existsByCourse_CourseIdNotAndStartTimeBetween(Long courseId, LocalDateTime startTime, LocalDateTime endTime);
}
