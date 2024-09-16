package com.attendance.attendance.dto;

import lombok.Data;

@Data
public class StudentDto extends PersonDto {
    private String studentNumber;

    private String departmentName;

    private String universityName;


}
