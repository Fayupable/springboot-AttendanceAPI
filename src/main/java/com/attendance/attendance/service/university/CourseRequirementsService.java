package com.attendance.attendance.service.university;

import com.attendance.attendance.dto.CoursesRequirementsDto;
import com.attendance.attendance.entity.CoursesRequirements;
import com.attendance.attendance.entity.UniversityCourse;
import com.attendance.attendance.repository.ICoursesRequirementsRepository;
import com.attendance.attendance.repository.IUniversityCourseRepository;
import com.attendance.attendance.request.university.course.requirement.AddCourseRequirementsRequest;
import com.attendance.attendance.request.university.course.requirement.UpdateCourseRequirementsRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseRequirementsService implements ICourseRequirementsService {

    private final ICoursesRequirementsRepository coursesRequirementsRepository;
    private final ModelMapper modelMapper;

    @Override
    public CoursesRequirements getCourseRequirementsById(Long id) {
        return coursesRequirementsRepository.findById(id).orElse(null);
    }

    @Override
    public CoursesRequirements getCourseRequirementsByCourseId(Long courseId) {
        return coursesRequirementsRepository.findByCourse_CourseId(courseId);
    }

    @Override
    public List<CoursesRequirements> getAllCourseRequirements() {
        return coursesRequirementsRepository.findAll();
    }

    @Override
    public List<CoursesRequirements> getCourseRequirementsByCourseName(String courseName) {
        return List.of();
    }

    @Override
    public CoursesRequirements addCourseRequirements(AddCourseRequirementsRequest request) {
        return Optional.of(request)
                .map(req -> {
                    createCourseRequirements(request);
                    return getCourseRequirementsByCourseId(request.getCourseId());
                }).orElseThrow(() -> new RuntimeException("Course not found"));
    }

    private void createCourseRequirements(AddCourseRequirementsRequest request) {
        CoursesRequirements coursesRequirements = new CoursesRequirements();
        coursesRequirements.setRequirementDescription(request.getCourseRequirements());
        UniversityCourse universityCourse = new UniversityCourse();
        universityCourse.setCourseId(request.getCourseId());
        coursesRequirements.setCourse(universityCourse);
        coursesRequirementsRepository.save(coursesRequirements);
    }


    @Override
    public CoursesRequirements updateCourseRequirements(UpdateCourseRequirementsRequest request, Long id) {
        return coursesRequirementsRepository.findById(id)
                .map(courseRequirements -> {
                    createUpdateCourseRequirements(request, id);
                    return getCourseRequirementsById(id);
                }).orElseThrow(() -> new RuntimeException("Course requirements not found"));
    }

    private void createUpdateCourseRequirements(UpdateCourseRequirementsRequest request, Long id) {
        CoursesRequirements coursesRequirements = coursesRequirementsRepository.findById(id).orElseThrow(() -> new RuntimeException("Course requirements not found"));
        coursesRequirements.setRequirementDescription(request.getCourseRequirements());
        coursesRequirementsRepository.save(coursesRequirements);
    }

    @Override
    public void deleteCourseRequirements(Long id) {
        coursesRequirementsRepository.findById(id).ifPresentOrElse(
                coursesRequirementsRepository::delete,
                () -> {
                    throw new RuntimeException("Course requirements not found");
                }
        );

    }

    @Override
    public void deleteCourseRequirementsByCourseId(Long courseId) {
        coursesRequirementsRepository.deleteByCourse_CourseId(courseId);
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
