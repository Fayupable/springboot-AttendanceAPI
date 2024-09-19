package com.attendance.attendance.request.teacher;

import com.attendance.attendance.request.person.AddPersonRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AddTeacherRequest extends AddPersonRequest {


    private Long universityId;

    private Long departmentId;

}
