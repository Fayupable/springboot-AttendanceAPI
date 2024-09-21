package com.attendance.attendance.repository;

import com.attendance.attendance.entity.CoursesRequirements;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICoursesRequirementsRepository extends JpaRepository<CoursesRequirements, Long> {
    boolean existsByCourse_CourseId(Long courseId);

    CoursesRequirements findByCourse_CourseId(Long courseId);

    void deleteByCourse_CourseId(Long courseId);

}
