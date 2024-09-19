package com.attendance.attendance.request.teacher;

import com.attendance.attendance.request.person.UpdatePersonRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateTeacherRequest extends UpdatePersonRequest {

    private Long universityId;

    private Long departmentId;
}

