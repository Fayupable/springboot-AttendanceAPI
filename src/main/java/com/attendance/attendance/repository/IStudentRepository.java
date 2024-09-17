package com.attendance.attendance.repository;

import com.attendance.attendance.entity.Student;
import com.attendance.attendance.entity.UniversityDepartment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IStudentRepository extends JpaRepository<Student, Long> {
    boolean existsByEmail(String email);

    Student findByEmail(String email);

    List<Student> findByEmailContaining(String email);

    List<Student> findByDepartment(UniversityDepartment department);

    List<Student> findStudentByEmail(String email);

    List<Student> findStudentByFirstNameContaining(String name);

    List<Student> findStudentByLastNameContaining(String name);


}
