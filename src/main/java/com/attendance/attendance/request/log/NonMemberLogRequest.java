package com.attendance.attendance.request.log;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(name = "NonMember Log Request", description = "Request to log non-member actions")
public class NonMemberLogRequest {
    private String action;
    private String method;
    private String path;
    private String ip;
    private String country;
    private String city;
    private String userAgent;
    private String deviceType;
    private String operatingSystem;
    private String browser;
    private String response;
    private String status;
    private String request;
    private String exception;
    private Long executionTime;
    private LocalDateTime loginTime;
    private String sessionId;
    private String applicationVersion;
    private String serverName;
}