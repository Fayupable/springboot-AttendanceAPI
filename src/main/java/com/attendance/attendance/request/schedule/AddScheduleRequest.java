package com.attendance.attendance.request.schedule;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(name = "Add Schedule Request", description = "Request to add a new schedule, including course ID, start time, end time, and location")
public class AddScheduleRequest {

    @Schema(description = "ID of the course", example = "12345")
    private Long courseId;

    @Schema(description = "Start time of the schedule", example = "2023-10-01T09:00:00")
    private LocalDateTime startTime;

    @Schema(description = "End time of the schedule", example = "2023-10-01T11:00:00")
    private LocalDateTime endTime;

    @Schema(description = "Location of the schedule", example = "Room 101")
    private String location;
}