package com.attendance.attendance.request.student;

import com.attendance.attendance.request.person.UpdatePersonRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateStudentRequest extends UpdatePersonRequest {

    private String studentNumber;

    private Long universityId;

    private Long departmentId;
}
