package com.attendance.attendance.request.person;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;

import java.util.Date;

@Data
@Schema(name = "Add Person Request", description = "The request to add a person, including date of birth, email, password, first name, and last name")
public class AddPersonRequest {

    @Schema(description = "Date of birth of the person", example = "1990-01-01")
    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    private Date dateOfBirth;

    @Schema(description = "Email of the person", example = "example@example.com")
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @Schema(description = "Password of the person", example = "password123")
    @NotBlank(message = "Password is required")
    private String password;

    @Schema(description = "First name of the person", example = "John")
    @NotBlank(message = "First name is required")
    private String firstName;

    @Schema(description = "Last name of the person", example = "Doe")
    @NotBlank(message = "Last name is required")
    private String lastName;
}