package com.attendance.attendance.service.university;

import com.attendance.attendance.dto.CoursesRequirementsDto;
import com.attendance.attendance.entity.CoursesRequirements;
import com.attendance.attendance.request.university.course.requirement.AddCourseRequirementsRequest;
import com.attendance.attendance.request.university.course.requirement.UpdateCourseRequirementsRequest;

import java.util.List;

public interface ICourseRequirementsService {
    CoursesRequirements getCourseRequirementsById(Long id);

    CoursesRequirements getCourseRequirementsByCourseId(Long courseId);

    CoursesRequirements addCourseRequirements(AddCourseRequirementsRequest request);

    CoursesRequirements updateCourseRequirements(UpdateCourseRequirementsRequest request, Long id);

    List<CoursesRequirements> getAllCourseRequirements();

    List<CoursesRequirements> getCourseRequirementsByCourseName(String courseName);

    void deleteCourseRequirements(Long id);

    void deleteCourseRequirementsByCourseId(Long courseId);

    CoursesRequirementsDto convertToDto(CoursesRequirements coursesRequirements);

    List<CoursesRequirementsDto> convertToDtoToList(List<CoursesRequirements> coursesRequirements);

}
