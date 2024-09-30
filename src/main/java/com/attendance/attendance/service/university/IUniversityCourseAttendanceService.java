package com.attendance.attendance.service.university;

import com.attendance.attendance.dto.CourseAttendanceDto;
import com.attendance.attendance.entity.CoursesAttendance;
import com.attendance.attendance.request.university.course.attendance.AddCourseAttendanceRequest;
import com.attendance.attendance.request.university.course.attendance.UpdateCourseAttendanceRequest;

import java.time.LocalDate;
import java.util.List;

public interface IUniversityCourseAttendanceService {

    CoursesAttendance getCourseAttendanceById(Long id);

    List<CoursesAttendance> getAllCourseAttendance();

    CoursesAttendance addCourseAttendance(AddCourseAttendanceRequest courseAttendance);

    CoursesAttendance updateCourseAttendance(UpdateCourseAttendanceRequest courseAttendance, Long id);

    void deleteCourseAttendance(Long id);

    CourseAttendanceDto convertToDto(CoursesAttendance coursesAttendance);

    List<CourseAttendanceDto> convertToDtoToList(List<CoursesAttendance> coursesAttendance);

    // Repository Methods
    boolean existsCourseAttendanceByStudentIdAndCourseIdAndAttendanceDate(Long studentId, Long courseId, LocalDate attendanceDate);

    List<CoursesAttendance> getCourseAttendanceByStudentIdAndCourseIdAndAttendanceDate(Long studentId, Long courseId, LocalDate attendanceDate);

    List<CoursesAttendance> getCourseAttendanceByStudentIdAndCourseId(Long studentId, Long courseId);

    List<CoursesAttendance> getCourseAttendanceByCourse(Long courseId);

    List<CoursesAttendance> getCourseAttendanceByCourseNameContaining(String courseName);

    List<CoursesAttendance> getCourseAttendanceByCourseId(Long courseId);

    List<CoursesAttendance> getCourseAttendanceByStudentId(Long studentId);

    List<CoursesAttendance> getCourseAttendanceByStudentIdAndCourseNameContaining(Long studentId, String courseName);
}