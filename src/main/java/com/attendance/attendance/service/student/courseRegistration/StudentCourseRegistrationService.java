package com.attendance.attendance.service.student.courseRegistration;

import com.attendance.attendance.dto.StudentCourseRegistrationDto;
import com.attendance.attendance.entity.StudentCourseRegistration;
import com.attendance.attendance.repository.IStudentCourseRegistrationRepository;
import com.attendance.attendance.request.student.courseRegistration.AddStudentCourseRegistrationRequest;
import com.attendance.attendance.request.student.courseRegistration.UpdateStudentCourseRegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentCourseRegistrationService implements IStudentCourseRegistrationService {
    private final IStudentCourseRegistrationRepository studentCourseRegistrationRepository;
    private final ModelMapper modelMapper;


    @Override
    public boolean existsByStudentIdAndCourseId(Long studentId, Long courseId) {
        return studentCourseRegistrationRepository.existsByStudentIdAndCourse_CourseId(studentId, courseId);
    }

    @Override
    public boolean existsByStudentIdAndCourseIdAndStatus(Long studentId, Long courseId, String status) {
        return studentCourseRegistrationRepository.existsByStudentIdAndCourse_CourseIdAndStatus(studentId, courseId, status);
    }

    @Override
    public boolean existsByStudentIdAndStatus(Long studentId, String status) {
        return studentCourseRegistrationRepository.existsByStudentIdAndStatus(studentId, status);
    }

    @Override
    public List<StudentCourseRegistration> getAllStudentCourseRegistrations() {
        return studentCourseRegistrationRepository.findAll();
    }

    @Override
    public List<StudentCourseRegistration> findByCourseId(Long courseId) {
        return studentCourseRegistrationRepository.findByCourse_CourseId(courseId);
    }

    @Override
    public List<StudentCourseRegistration> findByStudentId(Long studentId) {
        return studentCourseRegistrationRepository.findByStudentId(studentId);
    }

    @Override
    public List<StudentCourseRegistration> findByCourseIdAndStatus(Long courseId, String status) {
        return studentCourseRegistrationRepository.findByCourse_CourseIdAndStatus(courseId, status);
    }

    @Override
    public List<StudentCourseRegistration> findByStudentIdAndStatus(Long studentId, String status) {
        return studentCourseRegistrationRepository.findByStudentIdAndStatus(studentId, status);
    }

    @Override
    public List<StudentCourseRegistration> findByStudentIdAndCourseIdAndStatus(Long studentId, Long courseId, String status) {
        return studentCourseRegistrationRepository.findByStudentIdAndCourse_CourseIdAndStatus(studentId, courseId, status);
    }

    @Override
    public StudentCourseRegistration findByStudentIdAndCourseId(Long studentId, Long courseId) {
        return studentCourseRegistrationRepository.findByStudentIdAndCourse_CourseId(studentId, courseId);
    }

    @Override
    public StudentCourseRegistration addStudentCourseRegistration(AddStudentCourseRegistrationRequest studentCourseRegistration) {
        return null;
    }




    @Override
    public StudentCourseRegistration updateStudentCourseRegistration(UpdateStudentCourseRegistrationRequest request, Long studentCourseRegistrationId) {
        return null;
    }



    @Override
    public void deleteStudentCourseRegistration(Long studentCourseRegistrationId) {
        studentCourseRegistrationRepository.findById(studentCourseRegistrationId)
                .ifPresentOrElse(studentCourseRegistrationRepository::delete, () -> {
                    throw new RuntimeException("Student course registration not found");
                });
    }

    @Override
    public StudentCourseRegistrationDto convertToDto(StudentCourseRegistration studentCourseRegistration) {
        return modelMapper.map(studentCourseRegistration, StudentCourseRegistrationDto.class);
    }

    @Override
    public List<StudentCourseRegistrationDto> convertToDto(List<StudentCourseRegistration> studentCourseRegistrations) {
        return studentCourseRegistrations.stream()
                .map(this::convertToDto)
                .toList();
    }
}
