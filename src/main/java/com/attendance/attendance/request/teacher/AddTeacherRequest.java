package com.attendance.attendance.request.teacher;

import com.attendance.attendance.request.person.AddPersonRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Schema(name = "Add Teacher Request", description = "Request to add a new teacher, including personal details, university ID, and department ID")
public class AddTeacherRequest extends AddPersonRequest {

    @Schema(description = "ID of the university", example = "1")
    private Long universityId;

    @Schema(description = "ID of the department", example = "10")
    private Long departmentId;
}