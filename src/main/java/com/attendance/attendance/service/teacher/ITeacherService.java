package com.attendance.attendance.service.teacher;

import com.attendance.attendance.dto.TeacherDto;
import com.attendance.attendance.entity.Teacher;
import com.attendance.attendance.request.teacher.AddTeacherRequest;
import com.attendance.attendance.request.teacher.UpdateTeacherRequest;

import java.util.List;

public interface ITeacherService {
    List<Teacher> getAllTeachers();

    List<Teacher> getTeacherByEmail(String email);

    List<Teacher> getTeacherByName(String name);

    List<Teacher> getTeacherByUniversityId(Long universityId);

    List<Teacher> getTeacherByDepartmentId(Long departmentId);

    Teacher getTeacherById(Long id);

    Teacher addTeacher(AddTeacherRequest request);

    Teacher updateTeacher(UpdateTeacherRequest teacher, Long id);

    void deleteTeacher(Long id);

    TeacherDto convertToDto(Teacher teacher);

    List<TeacherDto> convertToDtoList(List<Teacher> teachers);
}
