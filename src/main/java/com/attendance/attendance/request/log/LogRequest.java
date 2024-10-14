package com.attendance.attendance.request.log;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "LogRequest", description = "Request object for logging")
public class LogRequest {
    private String serviceName;
    private Long personId;
    private String firstName;
    private String lastName;
    private String action;
    private String message;

}
