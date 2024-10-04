package com.attendance.attendance.dto;

import com.attendance.attendance.enums.RoleType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PersonDto {

    private String firstName;

    private String lastName;

    @JsonIgnore
    private String password;

    private Date dateOfBirth;

    private String email;

    private RoleType role;

    private List<ImageDto> images;

}
