package com.attendance.attendance.service.university;

import com.attendance.attendance.dto.CourseAttendanceDto;
import com.attendance.attendance.entity.CoursesAttendance;
import com.attendance.attendance.exceptions.AlreadyExistsException;
import com.attendance.attendance.exceptions.ResourceNotFoundException;
import com.attendance.attendance.repository.ICourseAttendanceRepository;
import com.attendance.attendance.repository.IStudentRepository;
import com.attendance.attendance.repository.IUniversityCourseRepository;
import com.attendance.attendance.request.university.course.attendance.AddCourseAttendanceRequest;
import com.attendance.attendance.request.university.course.attendance.UpdateCourseAttendanceRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UniversityCourseAttendanceService implements IUniversityCourseAttendanceService {
    private final ICourseAttendanceRepository courseAttendanceRepository;
    private final IStudentRepository studentRepository;
    private final IUniversityCourseRepository universityCourseRepository;
    private final ModelMapper modelMapper;


    @Override
    public CoursesAttendance getCourseAttendanceById(Long id) {
        return courseAttendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course attendance not found"));
    }

    @Override
    public List<CoursesAttendance> getAllCourseAttendance() {
        return courseAttendanceRepository.findAll();
    }

    @Override
    public List<CoursesAttendance> getCourseAttendanceByCourse(Long courseId) {
        return courseAttendanceRepository.findCoursesAttendanceByCourse_CourseId(courseId);
    }

    @Override
    public CoursesAttendance addCourseAttendance(AddCourseAttendanceRequest courseAttendance) {
        validateAttendance(courseAttendance);
        return Optional.of(courseAttendance)
                .map(this::createCourseAttendance)
                .map(courseAttendanceRepository::save)
                .orElseThrow(() -> new RuntimeException("Failed to add course attendance"));
    }

    private void validateAttendance(AddCourseAttendanceRequest request) {
        boolean exists = courseAttendanceRepository.existsCoursesAttendanceByStudent_IdAndCourse_CourseIdAndAttendanceDate(
                request.getStudentId(), request.getCourseId(), request.getAttendanceDate());

        if (exists) {
            throw new AlreadyExistsException("Attendance already exists for student ID: " + request.getStudentId() +
                    ", course ID: " + request.getCourseId() + " on date: " + request.getAttendanceDate());
        }
    }

    private CoursesAttendance createCourseAttendance(AddCourseAttendanceRequest request) {
        CoursesAttendance coursesAttendance = new CoursesAttendance();

        coursesAttendance.setStudent(studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + request.getStudentId())));

        coursesAttendance.setCourse(universityCourseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with ID: " + request.getCourseId())));

        coursesAttendance.setAttendanceDate(request.getAttendanceDate());
        coursesAttendance.setAttendanceStatus(request.getAttendanceStatus());

        return coursesAttendance;
    }


    @Override
    public CoursesAttendance updateCourseAttendance(UpdateCourseAttendanceRequest courseAttendance, Long id) {
        return courseAttendanceRepository.findById(id)
                .map(course -> {
                    course.setAttendanceStatus(courseAttendance.getAttendanceStatus());
                    return courseAttendanceRepository.save(course);
                })
                .orElseThrow(() -> new RuntimeException("Course attendance not found"));
    }

    @Override
    public void deleteCourseAttendance(Long id) {
        courseAttendanceRepository.findById(id)
                .ifPresentOrElse(courseAttendanceRepository::delete, () -> {
                    throw new RuntimeException("Course attendance not found");
                });

    }

    @Override
    public boolean existsCourseAttendanceByStudentIdAndCourseIdAndAttendanceDate(Long studentId, Long courseId, LocalDate attendanceDate) {
        return courseAttendanceRepository.existsCoursesAttendanceByStudent_IdAndCourse_CourseIdAndAttendanceDate(studentId, courseId, attendanceDate);
    }

    @Override
    public List<CoursesAttendance> getCourseAttendanceByStudentIdAndCourseIdAndAttendanceDate(Long studentId, Long courseId, LocalDate attendanceDate) {
        return courseAttendanceRepository.findCoursesAttendanceByStudent_IdAndCourse_CourseIdAndAttendanceDate(studentId, courseId, attendanceDate);
    }

    @Override
    public List<CoursesAttendance> getCourseAttendanceByStudentIdAndCourseId(Long studentId, Long courseId) {
        return courseAttendanceRepository.findCoursesAttendanceByStudent_IdAndCourse_CourseId(studentId, courseId);
    }


    @Override
    public List<CoursesAttendance> getCourseAttendanceByCourseNameContaining(String courseName) {
        return courseAttendanceRepository.findCoursesAttendanceByCourse_CourseNameContaining(courseName);
    }

    @Override
    public List<CoursesAttendance> getCourseAttendanceByCourseId(Long courseId) {
        return courseAttendanceRepository.findCoursesAttendanceByCourse_CourseId(courseId);
    }

    @Override
    public List<CoursesAttendance> getCourseAttendanceByStudentId(Long studentId) {
        return courseAttendanceRepository.findCoursesAttendanceByStudent_Id(studentId);
    }

    @Override
    public List<CoursesAttendance> getCourseAttendanceByStudentIdAndCourseNameContaining(Long studentId, String courseName) {
        return courseAttendanceRepository.findCoursesAttendanceByStudent_IdAndCourse_CourseNameContaining(studentId, courseName);
    }


    @Override
    public CourseAttendanceDto convertToDto(CoursesAttendance coursesAttendance) {
        return modelMapper.map(coursesAttendance, CourseAttendanceDto.class);
    }

    @Override
    public List<CourseAttendanceDto> convertToDtoToList(List<CoursesAttendance> coursesAttendance) {
        return coursesAttendance.stream()
                .map(this::convertToDto)
                .toList();
    }
}
