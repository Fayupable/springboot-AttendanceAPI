package com.attendance.attendance.request.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(name = "Login Request", description = "Request to log in, including email and password")
public class LoginRequest {

    @Schema(description = "Email of the user", example = "example@example.com")
    @NotBlank(message = "Email is required")
    private String email;

    @Schema(description = "Password of the user", example = "password123")
    @NotBlank(message = "Password is required")
    private String password;
}