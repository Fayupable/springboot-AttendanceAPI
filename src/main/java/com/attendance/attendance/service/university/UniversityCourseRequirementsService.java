package com.attendance.attendance.service.university;

import com.attendance.attendance.dto.CoursesRequirementsDto;
import com.attendance.attendance.dto.UniversityCourseDto;
import com.attendance.attendance.entity.CoursesRequirements;
import com.attendance.attendance.entity.UniversityCourse;
import com.attendance.attendance.exceptions.AlreadyExistsException;
import com.attendance.attendance.repository.IUniversityCourseRepository;
import com.attendance.attendance.repository.IUniversityCourseRequirementsRepository;
import com.attendance.attendance.request.university.course.course.AddUniversityCourseRequest;
import com.attendance.attendance.request.university.course.course.UpdateUniversityCourseRequest;
import com.attendance.attendance.request.university.course.requirement.AddCourseRequirementsRequest;
import com.attendance.attendance.request.university.course.requirement.UpdateCourseRequirementsRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UniversityCourseRequirementsService implements IUniversityCourseRequirementsService {

    private final IUniversityCourseRequirementsRepository universityCourseRequirementsRepository;
    private final IUniversityCourseRepository universityCourseRepository;
    private final ModelMapper modelMapper;

    @Override
    public CoursesRequirements getCourseRequirementsById(Long id) {
        return universityCourseRequirementsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course Requirements not found"));
    }

    @Override
    public List<CoursesRequirements> getAllCourseRequirements() {
        return universityCourseRequirementsRepository.findAll();
    }

    @Override
    public List<CoursesRequirements> getCourseRequirementsByCourseId(Long courseId) {
        return universityCourseRequirementsRepository.findByCourse_CourseId(courseId);
    }

    @Override
    public CoursesRequirements addCourseRequirements(AddCourseRequirementsRequest request) {
        return Optional.of(request)
                .filter(req -> !universityCourseRequirementsRepository.existsByRequirementDescriptionAndCourse_CourseId(req.getRequirementDescription(), req.getCourseId()))
                .map(req -> {
                    UniversityCourse course = findCourseById(req.getCourseId());
                    CoursesRequirements courseRequirements = createNewCourseRequirements(req, course);
                    return universityCourseRequirementsRepository.save(courseRequirements);
                })
                .orElseThrow(() -> new AlreadyExistsException("Requirement with description '" + request.getRequirementDescription() + "' already exists for course ID: " + request.getCourseId()));
    }



    private UniversityCourse findCourseById(Long courseId) {
        return universityCourseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found with ID: " + courseId));
    }

    private CoursesRequirements createNewCourseRequirements(AddCourseRequirementsRequest courseRequirements, UniversityCourse course) {
        CoursesRequirements newCourseRequirements = new CoursesRequirements();
        newCourseRequirements.setRequirementDescription(courseRequirements.getRequirementDescription());
        newCourseRequirements.setAttendancePercentage(courseRequirements.getAttendancePercentage());
        newCourseRequirements.setCourse(course); // Set the course
        return newCourseRequirements;
    }

    private CoursesRequirements saveCourseRequirements(CoursesRequirements courseRequirements) {
        return universityCourseRequirementsRepository.save(courseRequirements);
    }


    @Override
    public CoursesRequirements updateCourseRequirements(UpdateCourseRequirementsRequest request, Long id) {
        return null;
    }

    @Override
    public void deleteCourseRequirements(Long id) {

    }

    @Override
    public CoursesRequirementsDto convertToDto(CoursesRequirements coursesRequirements) {
        return modelMapper.map(coursesRequirements, CoursesRequirementsDto.class);
    }

    @Override
    public List<CoursesRequirementsDto> convertToDtoToList(List<CoursesRequirements> coursesRequirements) {
        return coursesRequirements.stream()
                .map(this::convertToDto)
                .toList();
    }
}
