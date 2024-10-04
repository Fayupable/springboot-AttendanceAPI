package com.attendance.attendance.request.schedule;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateScheduleRequest {
    private Long id;
    private Long courseId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String location;
}
