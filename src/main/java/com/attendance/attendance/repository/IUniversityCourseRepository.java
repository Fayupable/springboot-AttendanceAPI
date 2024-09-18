package com.attendance.attendance.repository;

import com.attendance.attendance.entity.University;
import com.attendance.attendance.entity.UniversityCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUniversityCourseRepository extends JpaRepository<UniversityCourse, Long> {
    boolean existsByCourseCode(String courseCode);

    boolean existsByCourseName(String courseName);

    List<UniversityCourse> findByCourseNameContaining(String courseName);

    boolean existsByCourseNameAndCourseCode(String courseName, String courseCode);

    boolean existsByCourseNameAndDepartment_University(String courseName, University university);

    boolean existsByCourseCodeAndDepartment_University(String courseCode, University university);

    UniversityCourse findByCourseCode(String courseCode);


    boolean existsByCourseNameAndCourseCodeAndDepartment_University(String courseName, String courseCode, University university);
}
