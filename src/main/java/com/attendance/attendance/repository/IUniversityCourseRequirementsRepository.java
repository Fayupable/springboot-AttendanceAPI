package com.attendance.attendance.repository;

import com.attendance.attendance.entity.CoursesRequirements;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUniversityCourseRequirementsRepository extends JpaRepository<CoursesRequirements, Long> {
    boolean existsByCourse_CourseId(Long courseId);

    List<CoursesRequirements> findByCourse_CourseId(Long courseId);

    boolean existsByRequirementDescriptionAndCourse_CourseId(String requirementDescription, Long courseId);
}
