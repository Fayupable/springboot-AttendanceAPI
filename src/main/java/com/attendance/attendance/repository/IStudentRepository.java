package com.attendance.attendance.repository;

import com.attendance.attendance.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStudentRepository extends JpaRepository<Student, Long> {
    boolean existsByEmail(String email);

    Student findByEmail(String email);

}
