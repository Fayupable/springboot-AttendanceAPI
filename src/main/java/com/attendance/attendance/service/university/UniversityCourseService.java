package com.attendance.attendance.service.university;

import com.attendance.attendance.dto.UniversityCourseDto;
import com.attendance.attendance.entity.University;
import com.attendance.attendance.entity.UniversityCourse;
import com.attendance.attendance.entity.UniversityDepartment;
import com.attendance.attendance.exceptions.AlreadyExistsException;
import com.attendance.attendance.repository.IDepartmentRepository;
import com.attendance.attendance.repository.IUniversityCourseRepository;
import com.attendance.attendance.repository.IUniversityRepository;
import com.attendance.attendance.request.university.course.course.AddUniversityCourseRequest;
import com.attendance.attendance.request.university.course.course.UpdateUniversityCourseRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.List;
import java.util.Optional;

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
        return Optional.of(course)
                .map(courseRequest -> {
                    UniversityDepartment department = findDepartmentById(course.getDepartmentId());
                    UniversityCourse universityCourse = createNewCourse(course, department);
                    return saveCourse(universityCourse);
                }).orElseThrow(() -> new IllegalArgumentException("Invalid course request"));
    }

    private void validateCourse(AddUniversityCourseRequest course) {
        UniversityDepartment department = findDepartmentById(course.getDepartmentId());
        validateCourseNameAndCode(course.getCourseName(), course.getCourseCode(), department.getUniversity().getUniversityId());
    }

    private void validateCourseNameAndCode(String courseName, String courseCode, Long universityId) {
        University university = findUniversityById(universityId);

        if (universityCourseRepository.existsByCourseNameAndCourseCodeAndDepartment_University(courseName, courseCode, university)) {
            throw new AlreadyExistsException("Course with name '" + courseName + "' and code '" + courseCode + "' already exists in university: " + university.getUniversityName());
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
    public UniversityCourse updateCourse(UpdateUniversityCourseRequest course, Long courseId, Long departmentId) {
        UniversityDepartment department = findDepartmentById(departmentId);
        UniversityCourse existingCourse = findCourseById(courseId);
        updateExistingCourse(existingCourse, course, department);
        return saveUpdatedCourse(existingCourse);
    }

    private UniversityCourse findCourseById(Long id) {
        return universityCourseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Course not found with ID: " + id));
    }

    private void updateExistingCourse(UniversityCourse existingCourse, UpdateUniversityCourseRequest course, UniversityDepartment department) {
        existingCourse.setCourseName(course.getCourseName());
        existingCourse.setCourseCode(course.getCourseCode());
        existingCourse.setDescription(course.getDescription());
        existingCourse.setDepartment(department);
    }

    private UniversityCourse saveUpdatedCourse(UniversityCourse course) {
        return universityCourseRepository.save(course);
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
