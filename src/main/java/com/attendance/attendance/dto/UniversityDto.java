package com.attendance.attendance.dto;

import lombok.Data;

import java.util.List;

@Data
public class UniversityDto {
    private String location;

    private String universityName;

    private List<UniversityDepartmentDto> departments;
}
