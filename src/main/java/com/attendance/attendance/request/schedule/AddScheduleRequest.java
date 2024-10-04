package com.attendance.attendance.request.schedule;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AddScheduleRequest {
    private Long courseId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String location;
}
