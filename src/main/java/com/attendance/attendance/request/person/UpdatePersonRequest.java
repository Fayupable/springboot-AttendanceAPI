package com.attendance.attendance.request.person;

import lombok.Data;

import java.util.Date;

@Data
public class UpdatePersonRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Date dateOfBirth;
}
