package com.attendance.attendance.service.student.courseRegistration;

import com.attendance.attendance.dto.StudentCourseRegistrationDto;
import com.attendance.attendance.entity.Student;
import com.attendance.attendance.entity.StudentCourseRegistration;
import com.attendance.attendance.entity.UniversityCourse;
import com.attendance.attendance.repository.IStudentCourseRegistrationRepository;
import com.attendance.attendance.request.student.courseRegistration.AddStudentCourseRegistrationRequest;
import com.attendance.attendance.request.student.courseRegistration.UpdateStudentCourseRegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
        return Optional.of(studentCourseRegistration)
                .map(this::createStudentCourseRegistration)
                .map(studentCourseRegistrationRepository::save)
                .orElseThrow();
    }

    private void setStudentCourseRegistrationFieldsForAdd(StudentCourseRegistration studentCourseRegistration, AddStudentCourseRegistrationRequest studentCourseRegistrationRequest) {
        Student student = new Student();
        student.setId(studentCourseRegistrationRequest.getStudentId());
        studentCourseRegistration.setStudent(student);
        UniversityCourse course = new UniversityCourse();
        course.setCourseId(studentCourseRegistrationRequest.getCourseId());
        studentCourseRegistration.setCourse(course);
        studentCourseRegistration.setRegistrationDate(LocalDateTime.from(studentCourseRegistrationRequest.getRegistrationDate()));
        studentCourseRegistration.setStatus(studentCourseRegistrationRequest.getStatus());
    }

    private StudentCourseRegistration createStudentCourseRegistration(AddStudentCourseRegistrationRequest studentCourseRegistrationRequest) {
        StudentCourseRegistration studentCourseRegistration = new StudentCourseRegistration();
        setStudentCourseRegistrationFieldsForAdd(studentCourseRegistration, studentCourseRegistrationRequest);
        return studentCourseRegistration;
    }


    @Override
    public StudentCourseRegistration updateStudentCourseRegistration(UpdateStudentCourseRegistrationRequest studentCourseRegistration, Long studentCourseRegistrationId) {
        return studentCourseRegistrationRepository.findById(studentCourseRegistration.getId())
                .map(studentCourseRegistration1 -> {
                    setStudentCourseRegistrationFieldsForUpdate(studentCourseRegistration1, studentCourseRegistration);
                    return studentCourseRegistrationRepository.save(studentCourseRegistration1);
                }).orElseThrow();
    }

    private void setStudentCourseRegistrationFieldsForUpdate(StudentCourseRegistration studentCourseRegistration, UpdateStudentCourseRegistrationRequest studentCourseRegistrationRequest) {
        Student student = new Student();
        student.setId(studentCourseRegistrationRequest.getStudentId());
        studentCourseRegistration.setStudent(student);
        UniversityCourse course = new UniversityCourse();
        course.setCourseId(studentCourseRegistrationRequest.getCourseId());
        studentCourseRegistration.setCourse(course);
        studentCourseRegistration.setRegistrationDate(LocalDateTime.from(studentCourseRegistrationRequest.getRegistrationDate()));
        studentCourseRegistration.setStatus(studentCourseRegistrationRequest.getStatus());
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
