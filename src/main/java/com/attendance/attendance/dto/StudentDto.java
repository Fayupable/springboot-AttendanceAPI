package com.attendance.attendance.dto;

import com.attendance.attendance.entity.UniversityCourse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class StudentDto extends PersonDto {
    private String studentNumber;

    private String departmentName;

    private String universityName;

}
