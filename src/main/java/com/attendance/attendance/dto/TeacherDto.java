package com.attendance.attendance.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TeacherDto extends PersonDto {

    private UniversityDto university;

    private UniversityDepartmentDto department;

}
