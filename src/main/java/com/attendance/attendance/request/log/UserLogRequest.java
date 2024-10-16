package com.attendance.attendance.request.log;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Schema(name = "User Log Request", description = "Request to log user actions")
public class UserLogRequest {
    @Schema(description = "ID of the associated person")
    private Long personId;

    @Schema(description = "Set of roles associated with the user")
    private Set<String> roles;

    @Schema(description = "Action performed by the user")
    private String action;

    @Schema(description = "HTTP method used")
    private String method;

    @Schema(description = "Path of the request")
    private String path;

    @Schema(description = "IP address of the user")
    private String ip;

    @Schema(description = "Country of the user")
    private String country;

    @Schema(description = "City of the user")
    private String city;

    @Schema(description = "User agent string")
    private String userAgent;

    @Schema(description = "Type of device used")
    private String deviceType;

    @Schema(description = "Operating system of the user")
    private String operatingSystem;

    @Schema(description = "Browser used by the user")
    private String browser;

    @Schema(description = "Response of the action")
    private String response;

    @Schema(description = "Status of the action")
    private String status;

    @Schema(description = "Request details")
    private String request;

    @Schema(description = "Exception details if any")
    private String exception;

    @Schema(description = "Execution time of the action")
    private Long executionTime;

    @Schema(description = "Login time of the user")
    private LocalDateTime loginTime;

    @Schema(description = "Session ID")
    private String sessionId;

    @Schema(description = "Version of the application")
    private String applicationVersion;

    @Schema(description = "Name of the server")
    private String serverName;
}