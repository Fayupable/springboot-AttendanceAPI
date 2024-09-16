package com.attendance.attendance.dto;

import com.attendance.attendance.enums.Role;
import lombok.Data;

import java.util.Date;

@Data
public class PersonDto {
    private Date dateOfBirth;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

}
