package com.attendance.attendance.service.university;

import com.attendance.attendance.dto.UniversityCourseDto;
import com.attendance.attendance.entity.University;
import com.attendance.attendance.entity.UniversityCourse;
import com.attendance.attendance.entity.UniversityDepartment;
import com.attendance.attendance.exceptions.AlreadyExistsException;
import com.attendance.attendance.repository.IDepartmentRepository;
import com.attendance.attendance.repository.IUniversityCourseRepository;
import com.attendance.attendance.repository.IUniversityRepository;
import com.attendance.attendance.request.university.course.AddUniversityCourseRequest;
import com.attendance.attendance.request.university.course.UpdateUniversityCourseRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UniversityCourseService implements IUniversityCourseService {

    private final IUniversityCourseRepository universityCourseRepository;
    private final IUniversityRepository universityRepository;
    private final IDepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    @Override
    public boolean existsByCourseName(String name) {
        return universityCourseRepository.existsByCourseName(name);
    }

    @Override
    public boolean existsByCourseCode(String code) {
        return universityCourseRepository.existsByCourseCode(code);
    }

    @Override
    public List<UniversityCourse> getAllCourses() {
        return universityCourseRepository.findAll();
    }

    @Override
    public UniversityCourse getCourseById(Long id) {
        return universityCourseRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Course not found"));
    }

    @Override
    public List<UniversityCourse> getCourseByName(String name) {
        return universityCourseRepository.findByCourseNameContaining(name);
    }

    @Override
    public List<UniversityCourse> getCourseByCode(String code) {
        return List.of(universityCourseRepository.findByCourseCode(code));
    }

    @Override
    public UniversityCourse addCourse(AddUniversityCourseRequest course) {
        validateCourse(course);
        UniversityDepartment department = findDepartmentById(course.getDepartmentId());
        UniversityCourse universityCourse = createNewCourse(course, department);
        return saveCourse(universityCourse);
    }

    private void validateCourse(AddUniversityCourseRequest course) {
        validateCourseNameAndCode(course.getCourseName(), course.getCourseCode());
        validateCourseNameInUniversity(course.getCourseName(), course.getUniversityId());
        validateCourseCodeInUniversity(course.getCourseCode(), course.getUniversityId());
    }

    private void validateCourseNameAndCode(String courseName, String courseCode) {
        if (universityCourseRepository.existsByCourseNameAndCourseCode(courseName, courseCode)) {
            throw new AlreadyExistsException("Course with name '" + courseName + "' and code '" + courseCode + "' already exists.");
        }
    }

    private void validateCourseNameInUniversity(String courseName, Long universityId) {
        University university = findUniversityById(universityId);
        if (universityCourseRepository.existsByCourseNameAndDepartment_University(courseName, university)) {
            throw new AlreadyExistsException("Course with name '" + courseName + "' already exists in university: " + university.getUniversityName());
        }
    }

    private void validateCourseCodeInUniversity(String courseCode, Long universityId) {
        University university = findUniversityById(universityId);
        if (universityCourseRepository.existsByCourseCodeAndDepartment_University(courseCode, university)) {
            throw new AlreadyExistsException("Course with code '" + courseCode + "' already exists in university: " + university.getUniversityName());
        }
    }

    private University findUniversityById(Long universityId) {
        return universityRepository.findById(universityId)
                .orElseThrow(() -> new IllegalArgumentException("University not found with ID: " + universityId));
    }

    private UniversityDepartment findDepartmentById(Long departmentId) {
        return departmentRepository.findById(departmentId)
                .orElseThrow(() -> new IllegalArgumentException("Department not found with ID: " + departmentId));
    }

    private UniversityCourse createNewCourse(AddUniversityCourseRequest course, UniversityDepartment department) {
        UniversityCourse universityCourse = new UniversityCourse();
        universityCourse.setCourseName(course.getCourseName());
        universityCourse.setCourseCode(course.getCourseCode());
        universityCourse.setDescription(course.getDescription());
        universityCourse.setDepartment(department);
        return universityCourse;
    }

    private UniversityCourse saveCourse(UniversityCourse course) {
        return universityCourseRepository.save(course);
    }


    @Override
    public UniversityCourse updateCourse(UpdateUniversityCourseRequest course, Long id) {
        return null;
    }

    @Override
    public void deleteCourse(Long id) {
        universityCourseRepository.findById(id).ifPresentOrElse(universityCourseRepository::delete, () -> {
            throw new IllegalArgumentException("Course not found");
        });
    }

    @Override
    public UniversityCourseDto convertToDto(UniversityCourse course) {
        return modelMapper.map(course, UniversityCourseDto.class);
    }

    @Override
    public List<UniversityCourseDto> convertToDtoToList(List<UniversityCourse> courses) {
        return courses.stream()
                .map(this::convertToDto)
                .toList();

    }
}
