package com.attendance.attendance.service.university;

import com.attendance.attendance.dto.UniversityCourseDto;
import com.attendance.attendance.entity.UniversityCourse;
import com.attendance.attendance.request.university.course.AddUniversityCourseRequest;
import com.attendance.attendance.request.university.course.UpdateUniversityCourseRequest;

import java.util.List;

public interface IUniversityCourseService {
    boolean existsByCourseName(String name);

    boolean existsByCourseCode(String code);

    List<UniversityCourse> getAllCourses();

    UniversityCourse getCourseById(Long id);

    List<UniversityCourse> getCourseByCode(String code);

    List<UniversityCourse> getCourseByName(String name);

    UniversityCourse addCourse(AddUniversityCourseRequest course);

    UniversityCourse updateCourse(UpdateUniversityCourseRequest course, Long id);

    void deleteCourse(Long id);

    UniversityCourseDto convertToDto(UniversityCourse course);

    List<UniversityCourseDto> convertToDtoToList(List<UniversityCourse> courses);


}
