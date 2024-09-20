package com.attendance.attendance.request.report.user;

import com.attendance.attendance.entity.Person;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateUserReportsRequest {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime date;
    private Long personId;

}
