package com.attendance.attendance.service.university;

import com.attendance.attendance.dto.CoursesRequirementsDto;
import com.attendance.attendance.entity.CoursesRequirements;
import com.attendance.attendance.request.university.course.requirement.AddCourseRequirementsRequest;
import com.attendance.attendance.request.university.course.requirement.UpdateCourseRequirementsRequest;

import java.util.List;

public interface IUniversityCourseRequirementsService {

    CoursesRequirements getCourseRequirementsById(Long id);

    List<CoursesRequirements> getAllCourseRequirements();

    List<CoursesRequirements> getCourseRequirementsByCourseId(Long courseId);

    CoursesRequirements addCourseRequirements(AddCourseRequirementsRequest courseRequirements);

    CoursesRequirements updateCourseRequirements(UpdateCourseRequirementsRequest request, Long id, Long courseId);

    void deleteCourseRequirements(Long id);

    CoursesRequirementsDto convertToDto(CoursesRequirements coursesRequirements);

    List<CoursesRequirementsDto> convertToDtoToList(List<CoursesRequirements> coursesRequirements);


}
