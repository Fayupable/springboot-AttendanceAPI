package com.attendance.attendance.repository;

import com.attendance.attendance.entity.University;
import com.attendance.attendance.entity.UniversityCourse;
import com.attendance.attendance.entity.UniversityCourseDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUniversityCourseDetailsRepository extends JpaRepository<UniversityCourseDetails, Long> {
    List<UniversityCourseDetails> findByCourse_CourseId(Long courseId);

    boolean existsByCourseAndDetailedDescription(UniversityCourse courseById, String detailedDescription);
}

