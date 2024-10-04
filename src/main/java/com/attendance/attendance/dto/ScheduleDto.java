package com.attendance.attendance.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ScheduleDto {
    private Long id;
    private Long courseId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String location;

}
