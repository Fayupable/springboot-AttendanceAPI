package com.attendance.attendance.service.student.courseRegistration;

import com.attendance.attendance.dto.StudentCourseRegistrationDto;
import com.attendance.attendance.entity.Student;
import com.attendance.attendance.entity.StudentCourseRegistration;
import com.attendance.attendance.entity.UniversityCourse;
import com.attendance.attendance.request.student.courseRegistration.AddStudentCourseRegistrationRequest;
import com.attendance.attendance.request.student.courseRegistration.UpdateStudentCourseRegistrationRequest;

import java.util.List;

public interface IStudentCourseRegistrationService {
    boolean existsByStudentIdAndCourseId(Long studentId, Long courseId);

    boolean existsByStudentIdAndCourseIdAndStatus(Long studentId, Long courseId, String status);

    boolean existsByStudentIdAndStatus(Long studentId, String status);

    boolean checkStudentStatusAndCourse(Long studentId, Long courseId);

    List<StudentCourseRegistration> getAllStudentCourseRegistrations();

    List<StudentCourseRegistration> findByCourseId(Long courseId);

    List<StudentCourseRegistration> findByStudentId(Long studentId);

    List<StudentCourseRegistration> findByCourseIdAndStatus(Long courseId, String status);

    List<StudentCourseRegistration> findByStudentIdAndStatus(Long studentId, String status);

    List<StudentCourseRegistration> findByStudentIdAndCourseIdAndStatus(Long studentId, Long courseId, String status);

    StudentCourseRegistration findByStudentIdAndCourseId(Long studentId, Long courseId);

    StudentCourseRegistration addStudentCourseRegistration(AddStudentCourseRegistrationRequest studentCourseRegistration);

    StudentCourseRegistration updateStudentCourseRegistration(UpdateStudentCourseRegistrationRequest studentCourseRegistration, Long studentCourseRegistrationId);

    void deleteStudentCourseRegistration(Long studentCourseRegistrationId);

    StudentCourseRegistrationDto convertToDto(StudentCourseRegistration studentCourseRegistration);

    List<StudentCourseRegistrationDto> convertToDto(List<StudentCourseRegistration> studentCourseRegistrations);


}
