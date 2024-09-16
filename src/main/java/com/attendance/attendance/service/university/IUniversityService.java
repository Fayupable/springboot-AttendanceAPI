package com.attendance.attendance.service.university;

import com.attendance.attendance.dto.UniversityDto;
import com.attendance.attendance.entity.University;
import com.attendance.attendance.request.university.AddUniversityRequest;

import java.util.List;

public interface IUniversityService {
    University getUniversityById(Long id);

    List<University> getAllUniversity();

    List<University> getUniversityByName(String name);

    University addUniversity(AddUniversityRequest request);

    University updateUniversity(University university, Long id);

    void deleteUniversity(Long id);

    boolean existsByUniversityName(String name);

    UniversityDto convertToDto(University university);

    List<UniversityDto> getConvertedUniversity(List<University> universities);
}
