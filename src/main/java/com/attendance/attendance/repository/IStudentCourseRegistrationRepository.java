package com.attendance.attendance.repository;

import com.attendance.attendance.entity.Student;
import com.attendance.attendance.entity.StudentCourseRegistration;
import com.attendance.attendance.entity.UniversityCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IStudentCourseRegistrationRepository extends JpaRepository<StudentCourseRegistration, Long> {
    StudentCourseRegistration findByStudentIdAndCourse_CourseId(Long studentId, Long courseId);

    List<StudentCourseRegistration> findByStudentId(Long studentId);

    List<StudentCourseRegistration> findByCourse_CourseId(Long courseId);

    List<StudentCourseRegistration> findByCourse_CourseIdAndStatus(Long courseId, String status);

    List<StudentCourseRegistration> findByStudentIdAndStatus(Long studentId, String status);

    List<StudentCourseRegistration> findByStudentIdAndCourse_CourseIdAndStatus(Long studentId, Long courseId, String status);

    boolean existsByStudentIdAndCourse_CourseId(Long studentId, Long courseId);

    boolean existsByStudentIdAndCourse_CourseIdAndStatus(Long studentId, Long courseId, String status);

    boolean existsByStudentIdAndStatus(Long studentId, String status);


}
