package com.attendance.attendance.service.student;

import com.attendance.attendance.dto.StudentDto;
import com.attendance.attendance.entity.Student;
import com.attendance.attendance.request.student.AddStudentRequest;


public interface IStudentService {
    Student getStudentByEmail(String email);

    Student getStudentByName(String name);

    Student getStudentById(Long id);

    Student addStudent(AddStudentRequest request);

    Student updateStudent(Student student, Long id);

    void deleteStudent(Long id);

    StudentDto convertToDto(Student student);
}
