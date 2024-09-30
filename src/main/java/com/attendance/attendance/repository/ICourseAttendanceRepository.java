package com.attendance.attendance.repository;

import com.attendance.attendance.entity.CoursesAttendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ICourseAttendanceRepository extends JpaRepository<CoursesAttendance, Long> {
    boolean existsCoursesAttendanceByStudent_IdAndCourse_CourseIdAndAttendanceDate(Long studentId, Long courseId, LocalDate attendanceDate);

    List<CoursesAttendance> findCoursesAttendanceByStudent_IdAndCourse_CourseIdAndAttendanceDate(Long studentId, Long courseId, LocalDate attendanceDate);

    List<CoursesAttendance> findCoursesAttendanceByStudent_IdAndCourse_CourseId(Long studentId, Long courseId);

    List<CoursesAttendance>  findCoursesAttendanceByCourse(CoursesAttendance course);

    List<CoursesAttendance>  findCoursesAttendanceByCourse_CourseNameContaining(String courseName);

    List<CoursesAttendance>  findCoursesAttendanceByCourse_CourseId(Long courseId);

    List<CoursesAttendance>  findCoursesAttendanceByStudent_Id(Long studentId);

    List<CoursesAttendance>  findCoursesAttendanceByStudent_IdAndCourse_CourseNameContaining(Long studentId, String courseName);
}
