package com.attendance.attendance.service.student;

import com.attendance.attendance.dto.StudentDto;
import com.attendance.attendance.entity.Student;
import com.attendance.attendance.request.student.AddStudentRequest;
import com.attendance.attendance.request.student.UpdateStudentRequest;

import java.util.List;


public interface IStudentService {

    List<Student> getAllStudents();

    List<Student> getStudentByEmail(String email);

    List<Student> getStudentByName(String name);

    Student getStudentById(Long id);


    Student addStudent(AddStudentRequest request);

    Student updateStudent(UpdateStudentRequest student, Long id);

    void deleteStudent(Long id);

    StudentDto convertToDto(Student student);

    List<StudentDto> convertToDtoList(List<Student> students);

}
