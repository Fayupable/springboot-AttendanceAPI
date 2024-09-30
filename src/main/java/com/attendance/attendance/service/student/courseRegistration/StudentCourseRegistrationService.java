package com.attendance.attendance.service.student.courseRegistration;

import com.attendance.attendance.dto.StudentCourseRegistrationDto;
import com.attendance.attendance.entity.StudentCourseRegistration;
import com.attendance.attendance.repository.IStudentCourseRegistrationRepository;
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
        return false;
    }

    @Override
    public boolean existsByStudentIdAndCourseIdAndStatus(Long studentId, Long courseId, String status) {
        return false;
    }

    @Override
    public boolean existsByStudentIdAndStatus(Long studentId, String status) {
        return false;
    }

    @Override
    public List<StudentCourseRegistration> getAllStudentCourseRegistrations() {
        return studentCourseRegistrationRepository.findAll();
    }

    @Override
    public List<StudentCourseRegistration> findByCourseId(Long courseId) {
        return List.of();
    }

    @Override
    public List<StudentCourseRegistration> findByStudentId(Long studentId) {
        return List.of();
    }

    @Override
    public List<StudentCourseRegistration> findByCourseIdAndStatus(Long courseId, String status) {
        return List.of();
    }

    @Override
    public List<StudentCourseRegistration> findByStudentIdAndStatus(Long studentId, String status) {
        return List.of();
    }

    @Override
    public List<StudentCourseRegistration> findByStudentIdAndCourseIdAndStatus(Long studentId, Long courseId, String status) {
        return List.of();
    }

    @Override
    public StudentCourseRegistration findByStudentIdAndCourseId(Long studentId, Long courseId) {
        return null;
    }

    @Override
    public StudentCourseRegistration addStudentCourseRegistration(StudentCourseRegistration studentCourseRegistration) {
        return null;
    }

    @Override
    public StudentCourseRegistration updateStudentCourseRegistration(StudentCourseRegistration studentCourseRegistration) {
        return null;
    }

    @Override
    public void deleteStudentCourseRegistration(Long studentCourseRegistrationId) {

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
