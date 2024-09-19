package com.attendance.attendance.repository;

import com.attendance.attendance.entity.Teacher;
import com.attendance.attendance.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITeacherRepository extends JpaRepository<Teacher, Long> {
    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email, Long id);

    List<Teacher> findByEmailContaining(String email);

    List<Teacher> findByFirstNameContaining(String name);

    List<Teacher> findByLastNameContaining(String name);

    List<Teacher> findTeacherByUniversity_UniversityId(Long universityId);

    List<Teacher> findTeacherByDepartment_DepartmentId(Long departmentId);

    Teacher findTeacherById(Long id);

}
