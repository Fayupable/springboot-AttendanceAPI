package com.attendance.attendance.request.teacher;

import com.attendance.attendance.request.person.UpdatePersonRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Schema(name = "Update Teacher Request", description = "Request to update an existing teacher, including personal details, university ID, and department ID")
public class UpdateTeacherRequest extends UpdatePersonRequest {

    @Schema(description = "ID of the university", example = "1")
    private Long universityId;

    @Schema(description = "ID of the department", example = "10")
    private Long departmentId;
}