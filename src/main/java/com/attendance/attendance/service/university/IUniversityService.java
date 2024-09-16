package com.attendance.attendance.service.university;

import com.attendance.attendance.entity.University;
import com.attendance.attendance.request.AddUniversityRequest;

public interface IUniversityService {
    University getUniversityById(Long id);

    University getUniversityByName(String name);

    University addUniversity(AddUniversityRequest request);

    University updateUniversity(University university, Long id);

    void deleteUniversity(Long id);

    boolean existsByUniversityName(String name);


}
