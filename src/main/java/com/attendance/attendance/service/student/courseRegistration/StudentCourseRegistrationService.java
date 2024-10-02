package com.attendance.attendance.service.student.courseRegistration;

import com.attendance.attendance.dto.StudentCourseRegistrationDto;
import com.attendance.attendance.entity.Student;
import com.attendance.attendance.entity.StudentCourseRegistration;
import com.attendance.attendance.entity.UniversityCourse;
import com.attendance.attendance.exceptions.AlreadyExistsException;
import com.attendance.attendance.repository.IStudentCourseRegistrationRepository;
import com.attendance.attendance.repository.IStudentRepository;
import com.attendance.attendance.repository.IUniversityCourseRepository;
import com.attendance.attendance.request.student.courseRegistration.AddStudentCourseRegistrationRequest;
import com.attendance.attendance.request.student.courseRegistration.UpdateStudentCourseRegistrationRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentCourseRegistrationService implements IStudentCourseRegistrationService {
    private final IStudentCourseRegistrationRepository studentCourseRegistrationRepository;
    private final IStudentRepository studentRepository;
    private final IUniversityCourseRepository universityCourseRepository;
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
    public boolean checkStudentStatusAndCourse(Long studentId, Long courseId) {
        return studentCourseRegistrationRepository.existsByStudentIdAndCourse_CourseIdAndStatus(studentId, courseId, "APPROVED");
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
    public StudentCourseRegistration addStudentCourseRegistration(AddStudentCourseRegistrationRequest request) {
        checkIfAlreadyRegistered(request.getStudentId(), request.getCourseId());
        return Optional.of(request)
                .filter(req -> req.getStudentId() != null && req.getCourseId() != null)
                .map(this::createStudentCourseRegistration)
                .map(studentCourseRegistrationRepository::save)
                .orElseThrow(() -> new RuntimeException("Invalid request"));
    }

    private void checkIfAlreadyRegistered(Long studentId, Long courseId) {
        boolean exists = studentCourseRegistrationRepository.existsByStudentIdAndCourse_CourseId(studentId, courseId);
        if (exists) {
            throw new AlreadyExistsException("This student is already registered for this course.");
        }
    }

    private StudentCourseRegistration createStudentCourseRegistration(AddStudentCourseRegistrationRequest request) {
        UniversityCourse course = universityCourseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));

        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));

        StudentCourseRegistration studentCourseRegistration = new StudentCourseRegistration();
        studentCourseRegistration.setCourse(course);
        studentCourseRegistration.setStudent(student);
        studentCourseRegistration.setStatus(request.getStatus());


        return studentCourseRegistration;
    }


    @Override
    public StudentCourseRegistration updateStudentCourseRegistration(UpdateStudentCourseRegistrationRequest request, Long studentCourseRegistrationId) {
        return studentCourseRegistrationRepository.findById(studentCourseRegistrationId)
                .map(studentCourseRegistration -> {
                    Student student = studentRepository.findById(request.getStudentId())
                            .orElseThrow(() -> new EntityNotFoundException("Student not found"));

                    UniversityCourse course = universityCourseRepository.findById(request.getCourseId())
                            .orElseThrow(() -> new EntityNotFoundException("Course not found"));

                    studentCourseRegistration.setStudent(student);
                    studentCourseRegistration.setCourse(course);
                    studentCourseRegistration.setRegistrationDate(request.getRegistrationDate());
                    studentCourseRegistration.setStatus(request.getStatus());
                    return studentCourseRegistrationRepository.save(studentCourseRegistration);
                })
                .orElseThrow(() -> new RuntimeException("Student course registration not found"));
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
