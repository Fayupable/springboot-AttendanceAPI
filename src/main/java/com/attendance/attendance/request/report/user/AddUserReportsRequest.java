package com.attendance.attendance.request.report.user;

import com.attendance.attendance.entity.Person;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AddUserReportsRequest {

    @NotNull(message = "Date is mandatory")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime date;

    @NotNull(message = "Person id is mandatory")
    private Long personId;

    @NotNull(message = "Title is mandatory")
    private String title;

    @NotNull(message = "Content is mandatory")
    private String content;



}
